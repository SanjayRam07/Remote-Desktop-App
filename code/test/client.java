
package test;

import java.awt.Graphics;
import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.*;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class client extends Thread {
    InputStream bis;
    Socket socket;
    Image image1;
    JFrame f;
    JLabel l;
    Graphics g;
    GraphicsEnvironment gEnv;
    GraphicsDevice gDev;
    Rectangle rectangle;

    client() {
        f=new JFrame();
        l=new JLabel();
        gEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
        
        gDev = gEnv.getDefaultScreenDevice();
        rectangle=gDev.getDefaultConfiguration().getBounds();
        try {
            socket=new Socket("172.22.11.119",5000);
            bis=socket.getInputStream();
           
        }
        catch(Exception e) {
            System.out.println(e);
        }
        g=f.getGraphics();
        f.setVisible(true);
        f.setLayout(null);
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
                image1= image1.getScaledInstance(f.getWidth(), f.getHeight(), java.awt.Image.SCALE_FAST);

                Graphics g=f.getGraphics();
                g.drawImage(image1,0,10,f.getWidth(),f.getHeight(),f);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        new client();
    }
}
