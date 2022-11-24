import java.awt.*;
import java.net.*;
import java.io.*;

public class InitConnection {

ServerSocket socket=null;
Socket sc;
DataInputStream password=null;
DataOutputStream verify=null;
String height="";
String width="";

InitConnection(int port,String value1) {
Robot robot=null;
Rectangle rectangle=null;

try {
System.out.println("waiting for client to get connected");
socket=new ServerSocket(port);

while(true) {
sc=socket.accept();
System.out.println("client connected");
password=new DataInputStream(sc.getInputStream());
verify=new DataOutputStream(sc.getOutputStream());

String psw=password.readUTF();
if(psw.equals(value1)) {
verify.writeUTF("valid");
verify.writeUTF(String.valueOf(rectangle.width));
verify.writeUTF(String.valueOf(rectangle.height));

robot=new Robot();
new SendScreen(sc,robot,rectangle);
new ReceiveEvents(sc,robot);
}
else {
verify.writeUTF("invalid");
}

}

}
catch(Exception e) {
System.out.println(e);
}

}

}
