package a.client;

import java.awt.Graphics;
import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class client extends Thread {
    InputStream bis;
    Socket socket;
    Image image1;
    JFrame f;
    JPanel panel,p2,p3,p4;
    JLabel l;
    Graphics g;
    GraphicsEnvironment gEnv;
    GraphicsDevice gDev;
    Rectangle rectangle;

    client(JPanel panel) {
        f=new JFrame();

        gEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
        gDev = gEnv.getDefaultScreenDevice();
        rectangle=gDev.getDefaultConfiguration().getBounds();

        this.panel=panel;
        p2=new JPanel();
        p3=new JPanel();
        p4=new JPanel();
        panel.setSize(rectangle.width/2,rectangle.height/2);
        p2.setSize(rectangle.width/2,rectangle.height/2);
        p3.setSize(rectangle.width/2,rectangle.height/2);
        p4.setSize(rectangle.width/2,rectangle.height/2);

        f.add(panel);
        f.add(p2);
        f.add(p3);
        f.add(p4);
        l=new JLabel();
    
        try {
            socket=new Socket("localhost",5001);
            bis=socket.getInputStream();
        }
        catch(Exception e) {
            System.out.println(e);
        }
        f.setVisible(true);
        panel.setVisible(true);
        f.setLayout(new GridLayout(2,2));
        f.setSize(rectangle.width,rectangle.height);
        start();
    }

    public void run() {
        try {
            while(true) {
                byte[] bytes=new byte[1024*1024];
                int count=0;
                do{
                    count+=bis.read(bytes,count,bytes.length-count);
                }while(!(count>4 && bytes[count-2]==(byte)-1 && bytes[count-1]==(byte)-39));

                image1 = ImageIO.read(new ByteArrayInputStream(bytes));
                //image1= image1.getScaledInstance(panel.getWidth(), panel.getHeight(), java.awt.Image.SCALE_FAST);

                Graphics g=panel.getGraphics();
                g.drawImage(image1,0,0,panel.getWidth(),panel.getHeight(),panel);
                 g=p2.getGraphics();
                g.drawImage(image1,0,0,panel.getWidth(),panel.getHeight(),p2);
                 g=p3.getGraphics();
                g.drawImage(image1,0,0,panel.getWidth(),panel.getHeight(),p3);
                 g=p4.getGraphics();
                g.drawImage(image1,0,0,panel.getWidth(),panel.getHeight(),p4);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        // new client();
    }
}
