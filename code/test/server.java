package test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.net.*;


public class server extends Thread {
    Robot robot;
    ServerSocket ss;
    Socket socket;
    GraphicsEnvironment gEnv;
    GraphicsDevice gDev;
    Rectangle rectangle;
    BufferedImage screenshot;
    OutputStream bos;

    server() {
        gEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
        gDev = gEnv.getDefaultScreenDevice();
        rectangle=gDev.getDefaultConfiguration().getBounds();

        try {
            ss=new ServerSocket(5000);
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
            BufferedImage image=robot.createScreenCapture(rectangle);
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
