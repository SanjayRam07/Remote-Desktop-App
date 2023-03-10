package client;

import java.awt.*;
import java.awt.event.*;
import java.net.*;
import javax.swing.*;

public class StartClient {

    Socket socket = null;

    public void initialize(String ip, int port ){

        Robot robot = null; 
        Rectangle rectangle = null;

        try {
            System.out.println("Connecting to server ..........");
            socket = new Socket(ip, port);
            System.out.println("Connection Established.");

            GraphicsEnvironment gEnv=GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice gDev=gEnv.getDefaultScreenDevice();

            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            rectangle = new Rectangle(dim);

            robot = new Robot(gDev);

            drawGUI();

            new ScreenSender(socket,robot,rectangle);
            new ActionReciever(socket,robot);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void drawGUI() {
        JFrame frame = new JFrame("Remote Admin");
        JButton button= new JButton("Terminate");
        
        frame.setBounds(100,100,150,150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(button);
        button.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        frame.setVisible(true);
    }

    public static void main(String[] args){
        String ip = JOptionPane.showInputDialog("Please enter server address");
        new StartClient().initialize(ip, 5000);
    }
}

