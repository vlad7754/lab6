package lab.Terminal;

import lab.lab34.Rocket;

import javax.xml.bind.JAXBException;
import java.util.Collections;

public class CommandImplement implements CommandAction {
    WorkFile w = new WorkFile();
    SerializableXML s = new SerializableXML();

    /**
     * info вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и элементы колекции
     */

    @Override
    public void infoAction() {

        System.out.println("В колекции находяться элементы типа Rocket\n");

        if (ColektionManager.getInstance().treeSet.size() == 1) {
            System.out.println("В колекции находиться " + ColektionManager.getInstance().treeSet.size() + " элемент\n");
        } else
            System.out.println("В колекции находиться " + ColektionManager.getInstance().treeSet.size() + " элементов\n");

        if (ColektionManager.getInstance().treeSet.size() > 0) {
            System.out.println("Элементы находящиеся в колекции:");
            ColektionManager.getInstance().treeSet.forEach(rocket -> System.out.println(rocket.toString()));
        }
    }

    /**
     * add_if_min {element}: добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции
     */
    @Override
    public void addIfMinAction(String s) {

        String[] strings = s.replaceAll("add_if_min", "")
                .replaceAll("\\{", "")
                .replaceAll("}", "").replaceAll("\"name\":", "")
                .replaceAll("\"fuel\":", "")
                .replaceAll(",", "").split(" ");

        try {

            Rocket rocket = new Rocket(strings[1], Integer.parseInt(strings[2]),strings[3].equals("true"));


            if (ColektionManager.getInstance().treeSet.size() == 0) {
                ColektionManager.getInstance().treeSet.add(rocket);
                System.out.println("Элемент добавлен в колекцию");
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
    }

    /**
     * clear: очистить коллекцию
     */
    @Override
    public void clearAction() {
        if (ColektionManager.getInstance().treeSet.size() == 0) {
            System.out.println(" Колекция пуста");
        } else {
            ColektionManager.getInstance().treeSet.clear();
            System.out.println("Элементы колекции были удалены");
        }
    }

    /**
     * import {String path}: добавить в коллекцию все данные из файла
     */
    @Override
    public void importAction(String string) throws JAXBException {

        try {
            s.disSerializ(w.readFile(string)).forEach(rocket -> ColektionManager.getInstance().treeSet.add(rocket));
            System.out.println("В коллекцию добавлены элементы из файла " + string);
        } catch (NullPointerException e) {
        }

    }

    /**
     * add {element}: добавить новый элемент в коллекцию
     */
    @Override
    public void addAction(String s) {


        String[] strings = s.replaceAll("add", "")
                .replaceAll("\\{", "")
                .replaceAll("}", "").replaceAll("\"name\":", "")
                .replaceAll("\"fuel\":", "")
                .replaceAll(",", "").split(" ");

        if (ColektionManager.getInstance().treeSet.contains(new Rocket(strings[1], Integer.parseInt(strings[2]),strings[3].equals(true)))) {
            System.out.println("Элемент уже существует");
            return;
        }

        try {


            ColektionManager.getInstance().treeSet.add(new Rocket(strings[1], Integer.parseInt(strings[2]),strings[3].equals("true")));
            System.out.println("Элемент  " + new Rocket(strings[1], Integer.parseInt(strings[2]),strings[3].equals("true")).toString() + "  добавлен в колекцию");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Данный элемент уже существует");
        } catch (NumberFormatException e) {
            System.out.println("Элемент задан не корректно");
        }

    }

    /**
     * remove {element}: удалить элемент из коллекции по его значению
     */
    @Override
    public void removeAction(String string) {

        if (ColektionManager.getInstance().treeSet.size() == 0) {
            System.out.println("В колекциии нет элементов");
        } else {

            String[] strings = string.replaceAll("remove", "")
                    .replaceAll("\\{", "")
                    .replaceAll("}", "").replaceAll("\"name\":", "")
                    .replaceAll("\"fuel\":", "")
                    .replaceAll(",", "").split(" ");

            try {
                int size = ColektionManager.getInstance().treeSet.size();
                ColektionManager.getInstance().treeSet.remove(new Rocket(strings[1], Integer.parseInt(strings[2]),strings[3].equals("true")));
                if (size == ColektionManager.getInstance().treeSet.size()) {
                    System.out.println("данного элемена нет в коллекции");
                } else
                    System.out.println("Элемент " + new Rocket(strings[1], Integer.parseInt(strings[2]),strings[3].equals("true")).toString() + " удолен из колекции");
            } catch (Exception e) {
                System.out.println("данного элемента нет в колекции");
            }
        }

    }

    /**
     * show: вывести в стандартный поток вывода все элементы коллекции в строковом представлении
     */
    @Override
    public void showAction() {
        if (ColektionManager.getInstance().treeSet.size() == 0) {
            System.out.println("Колекция пуста");
            return;
        }
        System.out.println("Элементы в колекции колекции:");

        ColektionManager.getInstance().treeSet.forEach(rocket -> System.out.println(rocket.toString()));

    }

    /**
     * Ввыводит информацию о командах
     */

    public void helpAction() {
        System.out.println("show: вывести в стандартный поток вывода все элементы коллекции в строковом представлении ");
        System.out.println("remove {element}: удалить элемент из коллекции по его значению");
        System.out.println("add {element}: добавить новый элемент в коллекцию ");
        System.out.println("import {String path}: добавить в коллекцию все данные из файла");
        System.out.println("clear: очистить коллекцию ");
        System.out.println("add_if_min {element}: добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции");
        System.out.println("info: вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и элементы колекции");
    }

}
