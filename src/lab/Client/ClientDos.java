package lab.Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientDos {
    public static void main(String[] args) throws IOException {
        String serverMessage;
        while (true){
            Socket s = new Socket("LocalHost",8888);
            DataInputStream inStream=new DataInputStream(s.getInputStream());
            DataOutputStream outStream=new DataOutputStream(s.getOutputStream());
            outStream.writeUTF("1scdrhrthr");
            outStream.flush();
            serverMessage=inStream.readUTF();
            System.out.println(serverMessage);
            outStream.close();
            inStream.close();
            s.close();
        }
    }
}
