package lab.Terminal;

import javax.xml.bind.JAXBException;

public interface CommandAction {

    void infoAction();

    void addIfMinAction(String string);

    void clearAction();

    void importAction(String string) throws JAXBException;

    void addAction(String string);

    void removeAction(String element);

    void showAction();

    void helpAction();


}
