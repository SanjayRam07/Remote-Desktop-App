import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.*;
import java.awt.event.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Authentication extends JFrame implements ActionListener {

    private Socket socket=null;
    DataOutputStream passchk=null;
    DataInputStream verification=null;
    String verify="";
    JButton submit;
    JPanel panel;
    JLabel label,label1;
    String width="", height="";
    JTextField text1;

    Authentication(Socket cSocket) {
        try {
            this.socket=cSocket;
            passchk=new DataOutputStream(socket.getOutputStream());
            verification=new DataInputStream(socket.getInputStream());
        }
        catch(Exception e) {
            e.printStackTrace();
        }

            label1=new JLabel();
            label1.setText("password");
            text1=new JTextField(15);
            label=new JLabel();
            label.setText("");

            submit=new JButton("submit");
            submit.addActionListener(this);
            panel=new JPanel();
            panel.setLayout(new GridLayout(2,2));

            panel.add(label1);
            panel.add(text1);
            panel.add(label);
            panel.add(submit);

            add(panel);
            setVisible(true);  
            setSize(500,500);;      
    }

    public void actionPerformed(ActionEvent e) {
        try {
            passchk.writeUTF(text1.getText());
            verify=verification.readUTF();
            if(verify.equals("valid")) {
                try {
                    width=verification.readUTF();
                    height=verification.readUTF();
                    System.out.printf(width,height);
                }
                catch(Exception e1) {
                    e1.printStackTrace();
                }
                CreateFrame abc=new CreateFrame(socket,width,height);
                dispose();
            }
        }
        catch(Exception e1) {
            System.out.println("writing error");
        }
    }
    
}
