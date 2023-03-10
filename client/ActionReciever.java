package client;

import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.*;

class ActionReciever extends Thread {

    Socket socket = null;
    Robot robot = null;
    boolean continueLoop = true;

    public ActionReciever(Socket socket, Robot robot) {
        this.socket = socket;
        this.robot = robot;
        start(); 
    }

    public void run(){
        Scanner scanner = null;
        try {
            System.out.println("Preparing InputStream");
            scanner = new Scanner(socket.getInputStream());

            while(continueLoop){
                System.out.println("Waiting for command");
                int command = scanner.nextInt();
                System.out.println("New command: " + command);

                switch(command){
                    case -1:
                        robot.mousePress(scanner.nextInt());
                    break;
                    case -2:
                        robot.mouseRelease(scanner.nextInt());
                    break;
                    case -3:
                        robot.keyPress(scanner.nextInt());
                    break;
                    case -4:
                        robot.keyRelease(scanner.nextInt());
                    break;
                    case -5:
                        robot.mouseMove(scanner.nextInt(), scanner.nextInt());
                    break;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
