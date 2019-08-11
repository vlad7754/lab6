package lab.Client;

import lab.Terminal.SerializableXML;
import lab.Terminal.WorkFile;
import lab.lab34.Rocket;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;
import java.util.TreeSet;

public class Client {
    public static void main(String[] args) throws Exception {
        try{
            SerializableXML s = new SerializableXML();
            WorkFile w = new WorkFile();

            Socket socket=new Socket("LocalHost",888);

            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());


            String clientMessage="",serverMessage="";
            System.out.println(ois.readUTF());
            while(!clientMessage.equals("exit")){
                String strings;
                Scanner scanner = new Scanner(System.in);
                clientMessage=scanner.next();

                TreeSet<Rocket> treeSet = new TreeSet<>();

//                if(clientMessage == "import") {
//                        try {
//
//                            oos.writeUTF("import");
//                            oos.flush();
//
//                            s.disSerializ(w.readFile(scanner.nextLine().replaceAll(" ", ""))).forEach(rocket -> treeSet.add(rocket));
//
//                            oos.writeObject(treeSet);
//                            oos.flush();
//
//                            continue;
//                        } catch (NullPointerException e) {
//                        }
//
//                } else {
//                oos.writeUTF(clientMessage);
//                oos.flush();}
//
//                serverMessage=ois.readUTF();
//                System.out.println(serverMessage);

                switch (clientMessage){
                    case("import") :

                        oos.writeUTF("import");
                        oos.flush();

                        s.disSerializ(w.readFile(scanner.nextLine().replaceAll(" ", ""))).forEach(r -> treeSet.add(r));

                        oos.writeObject(treeSet);
                        oos.flush();

                        System.out.println(ois.readUTF());

                        continue;
                    case ("add") :
                        oos.writeUTF(clientMessage);
                        oos.flush();

                        strings = scanner.nextLine().replaceAll("add", "")
                                    .replaceAll("\\{", "")
                                    .replaceAll("}", "").replaceAll("\"name\":", "")
                                    .replaceAll("\"fuel\":", "")
                                  .replaceAll(" ", "");
//                            .split(",");
//                       Rocket r = new Rocket(strings[0], Integer.parseInt(strings[1]),true);
//                        System.out.println(r.toString());
                       oos.writeUTF(strings);
                       oos.flush();
                       System.out.println(ois.readUTF());
                        continue;
                    case("info") :
                        oos.writeUTF("info");
                        oos.flush();

                        System.out.println(ois.readUTF());
                        continue;
                    case ("add_if_min") :
                        oos.writeUTF(clientMessage);
                        oos.flush();

                        strings = scanner.nextLine().replaceAll("add", "")
                                .replaceAll("\\{", "")
                                .replaceAll("}", "").replaceAll("\"name\":", "")
                                .replaceAll("\"fuel\":", "")
                                .replaceAll(" ", "");
//                            .split(",");
//                       Rocket r = new Rocket(strings[0], Integer.parseInt(strings[1]),true);
//                        System.out.println(r.toString());
                        oos.writeUTF(strings);
                        oos.flush();
                        System.out.println(ois.readUTF());
                        continue;
                    case("clear") :
                        oos.writeUTF("clear");
                        oos.flush();
                        System.out.println(ois.readUTF());
                        continue;
                    case ("remove") :
                        oos.writeUTF(clientMessage);
                        oos.flush();

                        strings = scanner.nextLine().replaceAll("add", "")
                                .replaceAll("\\{", "")
                                .replaceAll("}", "").replaceAll("\"name\":", "")
                                .replaceAll("\"fuel\":", "")
                                .replaceAll(" ", "");
//                            .split(",");
//                       Rocket r = new Rocket(strings[0], Integer.parseInt(strings[1]),true);
//                        System.out.println(r.toString());
                        oos.writeUTF(strings);
                        oos.flush();
                        System.out.println(ois.readUTF());
                        continue;
                    case("show") :
                        oos.writeUTF(clientMessage);
                        oos.flush();
                        System.out.println(ois.readUTF());

                        continue;
                   default:
                       System.out.println("Введена не существующая команда");
                }


            }
            System.out.println("Сессия закончена");
            ois.close();
            oos.close();
            socket.close();
        } catch (SocketException e){
            System.out.println("Сервер времено недоступен");
        } catch(Exception e){
            System.out.println(e);
        }
    }
}