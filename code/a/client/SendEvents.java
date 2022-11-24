package a.client;

import java.awt.event.*;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JPanel;

public class SendEvents implements KeyListener,MouseMotionListener,MouseListener {
    private Socket cSocket=null;
    private JPanel cPanel=null;
    private PrintWriter writer=null;
    String width="",height="";
    double w,h;

    SendEvents(Socket s,JPanel p,String width,String height) {
        cSocket=s;
        cPanel=p;
        this.width=width;
        this.height=height;
        w=Double.valueOf(width.trim()).doubleValue();
        h=Double.valueOf(height.trim()).doubleValue();

        cPanel.addKeyListener(this);
        cPanel.addMouseListener(this);

        try {
            writer=new PrintWriter(cSocket.getOutputStream());
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        writer.println(Commands.PRESS_MOUSE.getAbbrev());
        int button=e.getButton();
        int xButton=16;
        if(button==3) {
            xButton=4;
        }
        writer.println(xButton);
        writer.flush();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        writer.println(Commands.RELEASE_MOUSE.getAbbrev());
        int button=e.getButton();
        int xButton=16;
        if(button==3) {
            xButton=4;
        }
        writer.println(xButton);
        writer.flush();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        double xScale=(double)w/cPanel.getWidth();
        double yScale=(double)h/cPanel.getHeight();

        writer.println(Commands.MOVE_MOUSE.getAbbrev());
        writer.println((int)(e.getX()*xScale));
        writer.println((int)(e.getX()*yScale));
        writer.flush();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        writer.println(Commands.PRESS_KEY.getAbbrev());
        writer.println(e.getKeyCode());
        writer.flush();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        writer.println(Commands.RELEASE_KEY.getAbbrev());
        writer.println(e.getKeyCode());
        writer.flush();
    }
}
