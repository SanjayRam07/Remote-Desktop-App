package a.server;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;
import a.*;


public class server extends Thread {
    Robot robot;
    ServerSocket ss;
    Socket socket;
    GraphicsEnvironment gEnv;
    GraphicsDevice gDev;
    Rectangle rectangle;
    BufferedImage image;
    OutputStream bos;
    ByteArrayOutputStream baos;
    rsa r=new rsa();

    server() {
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
            try {
            ImageIO.write(image,"jpeg",bos);
            }
            catch(Exception e) {
            e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        new server();
    }
    
}
