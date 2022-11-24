package a.server;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;


public class server1 extends Thread {
    Robot robot;
    ServerSocket ss;
    Socket socket;
    GraphicsEnvironment gEnv;
    GraphicsDevice gDev;
    Rectangle rectangle;
    BufferedImage image;
    OutputStream bos;
    ByteArrayOutputStream baos;

    server1() {
        gEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
        gDev = gEnv.getDefaultScreenDevice();
        rectangle=gDev.getDefaultConfiguration().getBounds();

        try {
            ss=new ServerSocket(5001);
            socket=ss.accept();
            robot=new Robot();
            bos=socket.getOutputStream();
        }
        catch(Exception e) {
            System.out.println(e);
        }
        start();
    }

    public void run() {
        while(true) {
            image=robot.createScreenCapture(rectangle);
            baos=new ByteArrayOutputStream();
            try {
            ImageIO.write(image,"jpeg",baos);
            byte[] imageInByte=baos.toByteArray();
            bos.write(imageInByte);
            System.out.println(imageInByte);
            }
            catch(Exception e) {
            e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
       // new server();
    }
    
}
