package server;

import java.awt.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

class ClientHandler extends Thread {

    private JDesktopPane desktop = null;
    private Socket cSocket = null;
    private JInternalFrame interFrame = new JInternalFrame("Client Screen",false, false, false);
    private JPanel cPanel = new JPanel();
    
    public ClientHandler(Socket cSocket, JDesktopPane desktop) {
        ((javax.swing.plaf.basic.BasicInternalFrameUI)interFrame.getUI()).setNorthPane(null);
        this.cSocket = cSocket;
        this.desktop = desktop;
        start();
    }

    public void drawGUI(){
        interFrame.setLayout(new BorderLayout());
        interFrame.getContentPane().add(cPanel,BorderLayout.CENTER);
        interFrame.setSize(100,100);
        desktop.add(interFrame);
        try {
            interFrame.setMaximum(true);
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        cPanel.setFocusable(true);
        interFrame.setVisible(true);
    }

    public void run(){
        Rectangle clientScreenDim = null;
        ObjectInputStream ois = null;
        drawGUI();

        try{
            ois = new ObjectInputStream(cSocket.getInputStream());
            clientScreenDim =(Rectangle) ois.readObject();
        }catch(IOException ex){
            ex.printStackTrace();
        }catch(ClassNotFoundException ex){
            ex.printStackTrace();
        }
        new ScreenReciever(ois,cPanel);
        new ActionSender(cSocket,cPanel,clientScreenDim);
    }

}
