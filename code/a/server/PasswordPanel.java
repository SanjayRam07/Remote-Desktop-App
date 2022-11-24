package a.server;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PasswordPanel extends JPanel{

    JButton submit;
    JTextField text1;
    String value1="";
    JLabel label1, label2;

    public PasswordPanel() {
        label1 = new JLabel();
        label1.setText("Enter Password :");

        text1 = new JTextField(15);

        label2 = new JLabel();
        label2.setText("");

        submit = new JButton("submit");

        add(label1);
        add(text1);
        add(label2);
        add(submit);
        setVisible(true);
    }
}
