package a.server;

import java.awt.Robot;
import java.io.*;
import java.net.Socket;
import java.util.*;

public class ReceiveEvents extends Thread {
Socket socket=null;
Robot robot=null;
boolean continueLoop=true;
Scanner scanner=null;

public ReceiveEvents(Socket socket,Robot robot) {
this.socket=socket;
this.robot=robot;
try {
scanner=new Scanner(socket.getInputStream());
}
catch(Exception e) {
    e.printStackTrace();
}
start();
}

public void run() {

    try {

    while(continueLoop) {
    int command=scanner.nextInt();

    switch(command) {
    case 1:
    robot.mousePress(scanner.nextInt());
    break;
    case 2:
    robot.mouseRelease(scanner.nextInt());
    break;
    case 3:
    robot.keyPress(scanner.nextInt());
    break;
    case 4:
    robot.keyRelease(scanner.nextInt());
    break;
    case 5:
    robot.mouseMove(scanner.nextInt(),scanner.nextInt());
    break;
    }

}

}
catch(Exception e) {
e.printStackTrace();
}

}

}