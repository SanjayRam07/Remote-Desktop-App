package client;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

class ScreenSender extends Thread {

    Socket socket = null; 
    Robot robot = null; 
    Rectangle rectangle = null; 
    boolean continueLoop = true; 
    
    public ScreenSender(Socket socket, Robot robot,Rectangle rect) {
        this.socket = socket;
        this.robot = robot;
        rectangle = rect;
        start();
    }

    public void run(){
        ObjectOutputStream oos = null;

        try{
            oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(rectangle);
        }catch(IOException ex){
            ex.printStackTrace();
        }

       while(continueLoop){
            BufferedImage image = robot.createScreenCapture(rectangle);
            ImageIcon imageIcon = new ImageIcon(image);

            try {
                System.out.println("before sending image");
                oos.writeObject(imageIcon);
                oos.reset(); 
                System.out.println("New screenshot sent");
            } catch (IOException ex) {
               ex.printStackTrace();
            }
            try{
                Thread.sleep(100);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
