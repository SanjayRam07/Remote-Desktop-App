package test;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class test extends JFrame  {

    static Rectangle rectangle;

    public static void main(String args[]) {
        GraphicsEnvironment gEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
        
        GraphicsDevice gDev = gEnv.getDefaultScreenDevice();
        rectangle=gDev.getDefaultConfiguration().getBounds();
        System.out.println(rectangle.getWidth()+" "+rectangle.getHeight());
    }

}