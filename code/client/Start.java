import java.net.*;
import javax.swing.*;

import a.server.Start;

public class Start {
    static String port="4903";

    public static void main(String args[]) {
        String ip=JOptionPane.showInputDialog("please enter server ip address");
        new Start().initialise(ip,Integer.parseInt(port));
    }

    public void initialise(String ip,int port) {
        try {
            Socket sc=new Socket(ip,port);
            System.out.println("connected to server...");
            Authentication frame1=new Authentication(sc);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}