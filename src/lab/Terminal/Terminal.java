package lab.Terminal;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Terminal {


    public static void main(String[] args) throws JAXBException {
        Scanner scanner = new Scanner(System.in);
        CommandImplement commandImplement = new CommandImplement();
        WorkFile workFile = new WorkFile();
        SerializableXML s = new SerializableXML();


        System.out.println("Ввидите имя файла с которым будем работать");
        String file = scanner.next().replaceAll(" ", "");
        File f = new File(file);
        if (!f.exists()) {
            try {
                f.createNewFile();

                System.out.println("Файл создан");

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else System.out.println("Файл найден");



        s.disSerializ(workFile.readFile(file)).forEach(rocket -> ColektionManager.getInstance().treeSet.add(rocket));
        String str;
        while (true) {
            str = scanner.next();
            System.out.println(str);
            switch (str) {

                case "info":
                    commandImplement.infoAction();
                    break;
                case "add_if_min":
                    commandImplement.addIfMinAction(scanner.nextLine());
                    break;
                case "clear":
                    commandImplement.clearAction();
                    break;
                case "import":
                    commandImplement.importAction(scanner.nextLine());
                    break;
                case "add":
                    commandImplement.addAction(scanner.nextLine());
                    break;
                case "remove":
                    commandImplement.removeAction(scanner.nextLine());
                    break;
                case "show":
                    commandImplement.showAction();
                    break;
                case "help":
                    commandImplement.helpAction();
                 default:
                     if (str.equals("exit")){
                         ColektionManager.getInstance().treeSet.forEach(rocket -> workFile.writeFile(s.serializ(rocket), file));
                         return;
                     }else System.out.println("Команда не найдена, вы можите ввести команду help для получения информации о командах");

            }



        }


    }

}