package server;

import java.awt.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

public class StartServer {

    private JFrame frame = new JFrame();
    private JDesktopPane desktop = new JDesktopPane();

    public void start(int port){

        try {
            try (ServerSocket sc = new ServerSocket(port)) {
                drawGUI();

                while(true){
                    Socket client = sc.accept();
                    System.out.println("New client Connected to the server");
                    new ClientHandler(client,desktop);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void drawGUI(){
            frame.add(desktop,BorderLayout.CENTER);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setVisible(true);
    }

    public static void main(String args[]){
        int ch=JOptionPane.showConfirmDialog(null, "Start Server", "Start", 0);
        System.out.println(ch);
        if(ch==0){
            new StartServer().start(5000);
        }
        else{
            JOptionPane.showMessageDialog(null, "Cancelled", "Cancel", ch);
        }
    }
}
