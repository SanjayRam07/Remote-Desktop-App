package a.server;

import javax.swing.*;
import java.awt.*;

public class ConnectionPanel extends JPanel {
    JLabel label;

    public ConnectionPanel() {
        setLayout(new BorderLayout());

        label = new JLabel("waiting for client...");
        label.setHorizontalAlignment(SwingConstants.CENTER);

        add(label,BorderLayout.CENTER);

        setVisible(true);
    }

}
