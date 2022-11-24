package a.server;


import javax.swing.*;

import java.awt.*;

public class Start {
    static JFrame frame;

    Start() {
        Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
        frame=new JFrame();

        frame.setSize(d);
        frame.setVisible(true);
        frame.setTitle("Server");
        new SetPassword(frame);
    }

    public static void main(String args[]) {
        new Start();   
    }

}