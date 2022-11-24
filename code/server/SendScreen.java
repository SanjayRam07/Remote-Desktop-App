import java.awt.*;
import java.io.*;
import java.net.*;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;

public class SendScreen extends Thread {

Socket socket=null;
Robot robot=null;
Rectangle rectangle=null;
boolean continueLoop=true;
OutputStream oos=null;

public SendScreen(Socket socket,Robot robot,Rectangle rect) {
this.socket=socket;
this.robot=robot;
rectangle=rect;
start();
}

public void run() {
try {
oos=socket.getOutputStream();
}
catch(Exception e) 
{
e.printStackTrace();
}

while(continueLoop) {
BufferedImage image=robot.createScreenCapture(rectangle);
try {
ImageIO.write(image,"jpeg",oos);
}
catch(Exception e) {
e.printStackTrace();
}
try {
Thread.sleep(10);
}
catch(Exception e) {
e.printStackTrace();
}

}

}

}