package lab.Terminal;

import lab.lab34.Rocket;

import javax.xml.bind.*;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;

public class SerializableXML {


    String serializ(Rocket rocket) {


        //писать результат сериализации будем в Writer(StringWriter)
        StringWriter writer = new StringWriter();
        try {


            //создание объекта Marshaller, который выполняет сериализацию
            JAXBContext context = JAXBContext.newInstance(Rocket.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            // сама сериализация
            marshaller.marshal(rocket, writer);


        } catch (JAXBException e) {
            System.out.println("Проблемы с преоброзованием в XML формат");
        }
        //преобразовываем в строку все записанное в StringWriter
        String result = writer.toString();
        return result;
    }


    public ArrayList<Rocket> disSerializ(ArrayList<String> arrayList) throws JAXBException {

        ArrayList<Rocket> obj = new ArrayList<>();

        for (String xmldata : arrayList) {

            StringReader reader = new StringReader(xmldata);

            JAXBContext context = JAXBContext.newInstance(Rocket.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            Rocket rocket = (Rocket) unmarshaller.unmarshal(reader);

            obj.add(rocket);

        }

        return obj;
    }

}
