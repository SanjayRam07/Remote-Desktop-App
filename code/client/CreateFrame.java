import java.awt.BorderLayout;
import java.io.InputStream;
import java.net.Socket;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

import org.xml.sax.InputSource;

public class CreateFrame extends Thread {
    String width="",height="";
    private JFrame frame=null;
    private JDesktopPane desktop=new JDesktopPane();
    private Socket cSocket=new Socket();
    private JInternalFrame interFrame=new JInternalFrame("Server Screen",true,true,true);
    private JPanel cPanel=new JPanel();

    public CreateFrame(Socket cSocket,String width,String height) {
        this.width=width;
        this.height=height;
        this.cSocket=cSocket;
        frame=new JFrame();
    }

    public void drawGUI() {
        frame.setSize(Integer.parseInt(width),Integer.parseInt(height));
        frame.add(desktop,BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(frame.getExtendedState()|JFrame.MAXIMIZED_BOTH);
        try {
            interFrame.setMaximum(true);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        cPanel.setFocusable(true);
        interFrame.setVisible(true);
    }

    public void run() {
        InputStream in=null;
        drawGUI();
        try {
            in=cSocket.getInputStream();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        new ReceivingScreen(in,cPanel);
        new SendEvents(cSocket,cPanel,width,height);
    }
}
