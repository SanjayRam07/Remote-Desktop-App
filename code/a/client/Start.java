package a.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.Socket;
import javax.swing.*;

public class Start extends Thread {
    InputStream bis;
    Socket socket;
    JFrame frame=new JFrame();
    JPanel panel=new JPanel();

    Start() {
        frame.add(panel);
        try {
            socket=new Socket("localhost",5000); 
            System.out.println("socket connect");
            start();        
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

    public void run() {
        try {
            DataOutputStream os=new DataOutputStream(socket.getOutputStream());
            os.writeUTF("1234");
            new client(panel);
            new SendEvents(socket,panel,String.valueOf(frame.getHeight()),String.valueOf(frame.getHeight()));
            System.out.println("connected");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        new Start();
    }
}

