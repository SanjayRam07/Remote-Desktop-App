package a.server;

import java.awt.*;
import java.net.*;
import javax.swing.*;
import java.io.*;

public class InitConnection extends Thread{

    ConnectionPanel panel;
    JFrame frame;

    ServerSocket serverSocket = null;
    Socket socket;
    DataInputStream is = null;
    DataOutputStream os = null;

    Dimension dimension=Toolkit.getDefaultToolkit().getScreenSize();
    String pass;

    InitConnection(JFrame frame) {
        this.frame=frame;
        panel=new ConnectionPanel();

        frame.add(panel);
        start();
    }

    public void run() {
        connect();
    }

    private void connect() {
        try {
            serverSocket = new ServerSocket(5000);
            socket = serverSocket.accept();
            panel.label.setText("Client Joined");
            panel.label.setText("Checking for password...");
            checkPassword();
        } catch (Exception e) {
            System.out.println("connect");
        }
    }

    private void checkPassword() {
        String s="";
        try {
            is = new DataInputStream(socket.getInputStream());
            new server();
            new ReceiveEvents(socket,new Robot());
            while(s.length()<1){
                s=is.readUTF();
            }
            // if(pass.equals(s)) {
            //     new server();
            // }
            // else {
            //     panel.label.setText("Incorrect Password");
            // }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
