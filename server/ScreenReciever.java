package server;

import java.awt.*;
import java.io.*;
import javax.swing.*;

class ScreenReciever extends Thread {

    private ObjectInputStream cObjectInputStream = null;
    private JPanel cPanel = null;
    private boolean continueLoop = true;

    public ScreenReciever(ObjectInputStream ois, JPanel p) {
        cObjectInputStream = ois;
        cPanel = p;
        start();
    }

    public void run(){
        
        try {
                
            while(continueLoop){
                ImageIcon imageIcon = (ImageIcon) cObjectInputStream.readObject();
                System.out.println("New image recieved");
                Image image = imageIcon.getImage();
                image = image.getScaledInstance(cPanel.getWidth(),cPanel.getHeight(),Image.SCALE_FAST);
                Graphics graphics = cPanel.getGraphics();
                graphics.drawImage(image, 0, 0, cPanel.getWidth(),cPanel.getHeight(),cPanel);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
     }
}
