package utils;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonToHtml {

    public static void main(String[] args) {
        try {
            File file = new File("C:\\Users\\casa\\Desktop\\json_schedule.json");
            FileReader reader = new FileReader(file);
            JSONParser jsonParser = new JSONParser();
            Object obj = jsonParser.parse(reader);
            JSONObject jsonObject = (JSONObject) obj;

            // Cria uma página HTML que exibe as informações do arquivo JSON
            FileWriter htmlWriter = new FileWriter("pagina.html");
            htmlWriter.write("<html>\n");
            htmlWriter.write("<head>\n");
            htmlWriter.write("<title>Exemplo de abrir um arquivo JSON em Java</title>\n");
            htmlWriter.write("</head>\n");
            htmlWriter.write("<body>\n");
            htmlWriter.write("<table>\n");
            htmlWriter.write("<tr><th>Nome</th><th>Valor</th></tr>\n");
            for (Object key : jsonObject.keySet()) {
                htmlWriter.write("<tr><td>" + key + "</td><td>" + jsonObject.get(key) + "</td></tr>\n");
            }
            htmlWriter.write("</table>\n");
            htmlWriter.write("</body>\n");
            htmlWriter.write("</html>\n");
            htmlWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
