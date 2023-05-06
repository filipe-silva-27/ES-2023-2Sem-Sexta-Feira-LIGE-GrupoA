/*
package controllers;

import models.Aula;
import models.Horario;
import models.UnidadeCurricular;
import org.apache.commons.io.input.ObservableInputStream;

import org.apache.commons.text.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import gui.App;
import utils.URI.ExtractModelsFromWebcal;
import views.WebcalScheduleView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import gui.App;
import models.Aula;
import models.UnidadeCurricular;
import org.apache.commons.text.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import javax.swing.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static controllers.ShowScheduleController.exportAulasToJson;


public class WebcalScheduleController extends ViewController{
    private static String webcalURI = null;
    private static String webcalURI2 = null;
    private static boolean stateURI = false;
    private static final Logger logger = LoggerFactory.getLogger(WebcalScheduleController.class);

    public static String uri_max;

    public WebcalScheduleController(App app){
        super(app);
        logger.info("- inicializado com sucesso.");
    }

    public static void setInput(String input) {
        input = input;
    }

    public static String getUri_max(){
        return uri_max;
    }


    public void insertURI(){
       String input = JOptionPane.showInputDialog(null, "Enter the Webcal URI: ");
        uri_max = input;
        //logger.info(" isto é o insert uri --- " + input);
        if (input != null && !input.trim().isEmpty()) {
          //  logger.info(" iEntroiu no ruytghruibewirvje");
            try {
            //    logger.info(" iEntroiu no ruytghruibewirvje");
                // Parse the input as a URI
                String webcalURI = getWebcalURIFromUser(input);
                //WebcalScheduleView.setUri(webcalURI);

                // Load the calendar events from the Webcal URI using the TesteLeitorURI class
                loadScheduleFromWebcal(webcalURI);

                // For example, you could use the following code to display the calendar
                // events in a dialog box:
                JOptionPane.showMessageDialog(null, "Calendar events imported from:\n" + webcalURI);


            } catch (IOException e) {
                logger.error("Error importing Webcal URI", e);
                JOptionPane.showMessageDialog(null, "Error importing Webcal URI:\n" + e.getMessage());
            }
        }
    }




    public static String getWebcalURIFromUser(String input) {
        // Use the input provided as the URI
        webcalURI = input;
        stateURI=true;

        if (webcalURI.startsWith("webcal://")) {
            webcalURI = "https://" + webcalURI.substring(9);


        }

        return webcalURI;
    }

    public static void loadScheduleFromWebcal(String webcalURI2) throws IOException {
        // Criar um objeto URL a partir do Webcal URI
        URL url = new URL(getWebcalURIFromUser(webcalURI2));
        stateURI=true;


        // Abrir uma conexão HTTP para o URL
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Configurar a conexão para usar o método GET e aceitar a resposta como texto
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "text/calendar");

        // Verificar se a resposta foi bem sucedida (código 200 OK)
        if (connection.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + connection.getResponseCode());
        }

        // Ler a resposta da conexão e exibi-la na saída padrão
        BufferedReader br = new BufferedReader(new InputStreamReader(
                (connection.getInputStream())));

        String output;
        System.out.println("Output from Server .... \n");
        while ((output = br.readLine()) != null) {
            System.out.println(output);
        }

        // Fechar a conexão
        connection.disconnect();


    }

    public List<Aula> getAulas() {
        List<Aula> aulaList = new ArrayList<>();
        if (isHorarioSet()) {
            for(UnidadeCurricular uc : getHorario().getUnidadesCurriculares()){
                List<Aula> aulasAux = uc.getAulas();
                aulaList.addAll(aulasAux);
            }
        }
        Collections.sort(aulaList);
        logger.info("Aulas size: {}", aulaList.size());
        return aulaList;
    }

    public static void createHtmlView(List<Aula> aulas) {
        String json = exportAulasToJson(aulas);
        String escapedJson = StringEscapeUtils.escapeEcmaScript(json);

        // Load the HTML template
        InputStream templateStream = ShowScheduleController.class.getResourceAsStream("/calendar_template.html");
        if (templateStream != null) {
            try {
                Path tempFile = Files.createTempFile("calendar", ".html");
                Files.copy(templateStream, tempFile, StandardCopyOption.REPLACE_EXISTING);
                String htmlTemplate = Files.readString(tempFile, StandardCharsets.UTF_8);

                // Replace the JSON placeholder with the actual JSON data
                String htmlOutput = htmlTemplate.replace("{{aulas_json}}", escapedJson);
                Files.writeString(tempFile, htmlOutput, StandardCharsets.UTF_8);

                if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                    Desktop.getDesktop().browse(tempFile.toUri());
                } else {
                    JOptionPane.showMessageDialog(null, "Erro a abrir o browser!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Erro a criar o ficheiro HTML de visualização", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Não foi encontrado o template do calendário", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    public static boolean getURI(){
        return stateURI;
    }

    public static String getWebcalURIAsString() {
        return webcalURI;
    }
}*/
