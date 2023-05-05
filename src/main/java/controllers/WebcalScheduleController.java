package controllers;

import models.Aula;
import models.UnidadeCurricular;
import org.apache.commons.io.input.ObservableInputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import gui.App;

import java.io.BufferedReader;
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

    private static final Logger logger = LoggerFactory.getLogger(WebcalScheduleController.class);

    public WebcalScheduleController(App app){
        super(app);
        logger.info("- inicializado com sucesso.");
    }




    public static void loadScheduleFromWebcal(String webcalURI) throws IOException {
        // Criar um objeto URL a partir do Webcal URI
        URL url = new URL(webcalURI);

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

    public static String getWebcalURIFromUser(String input) {
        // Use the input provided as the URI
        String webcalURI = input;

        if (webcalURI.startsWith("webcal://")) {
            webcalURI = "https://" + webcalURI.substring(9);
            // Add "https://" prefix if necessary
            // if (!webcalURI.startsWith("https://")) {
            //   webcalURI = "https://" + webcalURI;
        }

        return webcalURI;
    }
}