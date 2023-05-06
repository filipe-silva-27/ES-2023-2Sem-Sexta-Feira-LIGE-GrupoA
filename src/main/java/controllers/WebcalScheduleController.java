/*
package controllers;

import models.Aula;
import models.Horario;
import models.UnidadeCurricular;
import org.apache.commons.io.input.ObservableInputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import gui.App;
import utils.URI.ExtractModelsFromWebcal;
import views.WebcalScheduleView;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class WebcalScheduleController extends ViewController{
    private static String webcalURI = null;
    private static String webcalURI2 = null;
    private static boolean stateURI = false;
    private static final Logger logger = LoggerFactory.getLogger(WebcalScheduleController.class);

    public WebcalScheduleController(App app){
        super(app);
        logger.info("- inicializado com sucesso.");
    }


    public void insertURI(){
        String input = JOptionPane.showInputDialog(null, "Enter the Webcal URI: ");
        if (input != null && !input.trim().isEmpty()) {
            try {
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


    /*public static void createHtmlView(List<Aula> aulas){
        String aulasURI =

    }*/




    /* public static void createHtml(Horario horario) {
         // Construct HTML content
         String htmlContent = horario.toHtml();

         // Write HTML content to file
         try {
             FileWriter writer = new FileWriter("output.html");
             writer.write(htmlContent);
             writer.close();
         } catch (IOException e) {
             System.out.println("Error writing HTML file: " + e.getMessage());
         }

         // Open HTML file in browser
         try {
             String os = System.getProperty("os.name").toLowerCase();
             Runtime rt = Runtime.getRuntime();
             if (os.contains("win")) {
                 rt.exec("cmd /c start output.html");
             } else if (os.contains("mac")) {
                 rt.exec("open output.html");
             } else if (os.contains("nix") || os.contains("nux")) {
                 rt.exec("xdg-open output.html");
             }
         } catch (IOException e) {
             System.out.println("Error opening HTML file in browser: " + e.getMessage());
         }
     }*/
   /* public static void createHtml() {
        // Extract parts from URI
        // For example, extract the hostname and path
        String[] parts = webcalURI.split("/");
        String hostname = parts[2];
        String path = "/" + parts[3];

        // Construct HTML content
        String htmlContent = "<html><body><h1>URI Information</h1>" +
                "<p>Hostname: " + hostname + "</p>" +
                "<p>Path: " + path + "</p></body></html>";

        // Write HTML content to file
        try {
            FileWriter writer = new FileWriter("output.html");
            writer.write(htmlContent);
            writer.close();
        } catch (IOException e) {
            System.out.println("Error writing HTML file: " + e.getMessage());
        }

        // Open HTML file in browser
        try {
            String os = System.getProperty("os.name").toLowerCase();
            Runtime rt = Runtime.getRuntime();
            if (os.contains("win")) {
                rt.exec("cmd /c start output.html");
            } else if (os.contains("mac")) {
                rt.exec("open output.html");
            } else if (os.contains("nix") || os.contains("nux")) {
                rt.exec("xdg-open output.html");
            }
        } catch (IOException e) {
            System.out.println("Error opening HTML file in browser: " + e.getMessage());
        }
    }*/


    public static boolean getURI(){
        return stateURI;
    }

}