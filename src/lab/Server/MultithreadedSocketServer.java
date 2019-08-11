package lab.Server;

import java.net.*;

public class MultithreadedSocketServer {
    public static void main(String[] args) {
        try{
            ServerSocket server=new ServerSocket(888);
            int counter=0;
            System.out.println("Server Started ....");
            while(true){
                counter++;
                Socket serverClient=server.accept();  // сервер принимает запрос на подключение клиента
                System.out.println(" >> " + "Клиент №:" + counter + " started!");
                ServerClientThread sct = new ServerClientThread(serverClient,counter); // отправляем запрос в отдельный поток
                sct.start();
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
}