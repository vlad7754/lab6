package lab.Terminal;
import java.io.*;
import java.util.ArrayList;


public class WorkFile {



   public ArrayList<String> readFile(String s){
      s = s.replaceAll(" ", "");

       ArrayList<String> arrayList = new ArrayList<>();


       try {
            InputStream is = new FileInputStream(s);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
           StringBuilder sb = new StringBuilder();
           while ((line=br.readLine())!=null){
                sb.append(line);
                if (line.matches(".*</rocket>.*")){
                    arrayList.add(sb.toString());
                    sb.setLength(0);
                }

          }

            br.close();

        } catch (IOException e) {

           System.out.println("Файл не найден, возможно:\n   имя файла содержит прообелы\n   не правельно указано имя файла");
           return null;
       }

       return arrayList;
   }



    void writeFile(String text, String file){

       file = file.replaceAll(" ","");

        try(FileOutputStream out = new FileOutputStream(file,true);
            BufferedOutputStream bos = new BufferedOutputStream(out))
        {
            // перевод строки в байты
            byte[] buffer = text.getBytes();
            bos.write(buffer, 0, buffer.length);
            bos.flush();
        }
        catch(IOException ex){

            System.out.println("Файл не найден, возможно:\n   имя файла содержит прообелы\n   не правельно указано имя файла");
        }

    }

}
