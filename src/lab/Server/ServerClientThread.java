package lab.Server;

import lab.Terminal.ColektionManager;
import lab.lab34.Rocket;

import java.io.*;
import java.net.*;
import java.util.Collections;
import java.util.TreeSet;

class ServerClientThread extends Thread {
    Socket serverClient;
    int clientNo;

    ServerClientThread(Socket inSocket, int counter) {
        serverClient = inSocket;
        clientNo = counter;
    }

    public void run() {
        try {


            ObjectInputStream ois = new ObjectInputStream(serverClient.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(serverClient.getOutputStream());

            oos.writeUTF("Вы подключились к серверу");
            oos.flush();

            String clientMessage = "", serverMessage = "";
            while (!clientMessage.equals("exit")) {
                String[] strings;
                Rocket rocket;
                clientMessage = ois.readUTF();
                System.out.println("Клиент-" + clientNo + ": Ввел команду:" + clientMessage);

                    switch (clientMessage) {

                        case "help":
                            oos.writeUTF(
                                "show: вывести в стандартный поток вывода все элементы коллекции в строковом представлении \n" +
                                        "remove {element}: удалить элемент из коллекции по его значению\n" +
                                        "add {element}: добавить новый элемент в коллекцию \n" +
                                        "import {String path}: добавить в коллекцию все данные из файла\n" +
                                        "clear: очистить коллекцию \n" +
                                        "add_if_min {element}: добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции\n" +
                                        "info: вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и элементы колекции\n");
                            oos.flush();
                            continue;

                        case "info":
                            System.out.println("В колекции находяться элементы типа Rocket\n");

                            if (ColektionManager.getInstance().treeSet.size() == 1) {
                                oos.writeUTF("В колекции находиться " + ColektionManager.getInstance().treeSet.size() + " элемент\n");
                                oos.flush();
                            } else
                                oos.writeUTF("В колекции находиться " + ColektionManager.getInstance().treeSet.size() + " элементов\n");
                                oos.flush();

                            if (ColektionManager.getInstance().treeSet.size() > 0) {
                                oos.writeUTF("Элементы находящиеся в колекции:");
                                oos.flush();
                                ColektionManager.getInstance().treeSet.forEach(r -> {
                                    try {
                                        oos.writeUTF(r.toString());
                                        oos.flush();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                });
                            }
                            continue;
                        case "add_if_min":
                            strings = ois.readUTF().split(",");
                            rocket = new Rocket(strings[0],Integer.parseInt(strings[1]), true);

                            try {
                                if (ColektionManager.getInstance().treeSet.size() == 0) {
                                    ColektionManager.getInstance().treeSet.add(rocket);
                                    oos.writeUTF("Элемент добавлен в колекцию");
                                    oos.flush();
                                    return;

                                }
                                try {


                                    if (rocket.compareTo(Collections.min(ColektionManager.getInstance().treeSet)) < 0) {
                                        ColektionManager.getInstance().treeSet.add(rocket);
                                        System.out.println("Элемент добавлен в коллекцию");
                                    } else System.out.println("Элемент не минемален\nЭлемент не добавлен в колекцию");

                                } catch (ArrayIndexOutOfBoundsException e) {
                                    System.out.println("Данный элемент уже существует");
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Элемент задан не корректно");
                            }


                            continue;
                        case "clear":
                            if (ColektionManager.getInstance().treeSet.size() == 0) {
                                oos.writeUTF(" Колекция пуста");
                                oos.flush();
                            } else {
                                ColektionManager.getInstance().treeSet.clear();
                                oos.writeUTF("Элементы колекции были удалены");
                                oos.flush();
                            }
                            continue;
                        case "import":

                            TreeSet<Rocket> ts = (TreeSet<Rocket>) ois.readObject();
                            ts.forEach(r -> ColektionManager.getInstance().treeSet.add(r));
                             oos.writeUTF("Import успешно выполнен");
                             oos.flush();
                            continue;
                        case "add":
                            strings = ois.readUTF().split(",");
                            rocket = new Rocket(strings[0],Integer.parseInt(strings[1]), true);

                            if (ColektionManager.getInstance().treeSet.contains(rocket)) {
                                oos.writeUTF("Элемент уже существует");
                                oos.flush();
                                continue;
                            }

                            try {
                                ColektionManager.getInstance().treeSet.add(rocket);
                                oos.writeUTF("Элемент  " + rocket.toString() + "  добавлен в колекцию");
                                oos.flush();
                            } catch (ArrayIndexOutOfBoundsException e) {
                                oos.writeUTF("Данный элемент уже существует");
                                oos.flush();
                            } catch (NumberFormatException e) {
                                oos.writeUTF("Элемент задан не корректно");
                                oos.flush();
                            }

                        case "remove":

                            strings = ois.readUTF().split(",");

                            if (ColektionManager.getInstance().treeSet.size() == 0) {
                                System.out.println("В колекциии нет элементов");
                            } else {


                                try {
                                    int size = ColektionManager.getInstance().treeSet.size();
                                    ColektionManager.getInstance().treeSet.remove(new Rocket(strings[0], Integer.parseInt(strings[1]),true));
                                    if (size == ColektionManager.getInstance().treeSet.size()) {
                                        oos.writeUTF("данного элемена нет в коллекции");
                                        oos.flush();
                                    } else
                                        oos.writeUTF("Элемент " + new Rocket(strings[0], Integer.parseInt(strings[1]),true).toString() + " удолен из колекции");
                                    oos.flush();
                                } catch (Exception e) {
                                    oos.writeUTF("данного элемента нет в колекции");
                                    oos.flush();
                                }
                            }

                            continue;
                        case "show":
                            System.out.println("show");
                            if (ColektionManager.getInstance().treeSet.size() == 0) {
                                oos.writeUTF("Коллекция пуста");
                                oos.flush();

                                continue;
                            }
                            ColektionManager.getInstance().treeSet.forEach(r -> {
                                try {
                                    oos.writeUTF(r.toString());
                                    oos.flush();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                      });
                            continue;
                         default:
                             oos.writeUTF("Данной команды не существует");
                    }
                }

            System.out.println(clientNo + "Сессия закончена");
            ois.close();
            oos.close();
            serverClient.close();
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            System.out.println("Client -" + clientNo + " exit!! ");
        }
    }
}

