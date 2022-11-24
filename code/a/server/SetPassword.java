package a.server;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SetPassword implements ActionListener {

    JFrame frame=new JFrame();
    PasswordPanel panel;
    Dimension d=Toolkit.getDefaultToolkit().getScreenSize();

    SetPassword(JFrame frame) {
        this.frame=frame;
        panel=new PasswordPanel();
        
        frame.add(panel);

        panel.submit.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        String value1 = panel.text1.getText();
        panel.setVisible(false);

        new InitConnection(frame);
    }


}