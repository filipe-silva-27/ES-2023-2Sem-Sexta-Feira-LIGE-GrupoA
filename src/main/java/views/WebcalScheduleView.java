/*
package views;

import controllers.*;
import models.Horario;
import net.fortuna.ical4j.data.ParserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import controllers.ShowScheduleController;
import controllers.ViewController;
import models.Aula;
import org.apache.commons.text.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.URI.ExtractModelsFromWebcal;
import utils.URI.URIToWebcal;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.util.List;
import java.util.TimerTask;
import java.util.Timer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


import javax.swing.*;

public class WebcalScheduleView extends View {

    private static final Logger logger = LoggerFactory.getLogger(WebcalScheduleView.class);

    private static String uri = null ;// URI.create(WebcalScheduleController.getWebcalURI());

    */
/**
     * Construtor da classe ShowScheduleView.
     *
     * @param viewController O controlador da view.
     *//*

    public WebcalScheduleView(ViewController viewController) {

        super(viewController);
    }


    */
/**
     * Inicializa o frame da view.
     *//*

    @Override
    public void initFrame() {

        this.removeAll();
        logger.info("Inicializando a frame da view MainMenuView");
        JButton inserirBtn = new JButton("Inserir URI");
        JButton verBtn = new JButton("Ver Horário");
        // Horario horario = CreateScheduleController.setHorario();
        TimerTask task = new TimerTask() {
            public void run() {
                if (!WebcalScheduleController.getURI()) {
                    verBtn.setVisible(false);
                } else {
                    verBtn.setVisible(true);
                }
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 0, 1000);

        inserirBtn.addActionListener(e -> ((WebcalScheduleController) viewController).insertURI());

        verBtn.addActionListener(e -> {
            uri = WebcalScheduleController.getUri_max();
            if (uri != null) {
                if (uri.startsWith("webcal://")) {
                    uri = "https://" + uri.substring(9);
                }
                List<Aula> aulas = ExtractModelsFromWebcal.getAulasFromWebcal(uri);
                if (aulas != null) {
                    WebcalScheduleController.createHtmlView(aulas);
                } else {
                    JOptionPane.showMessageDialog(null, "Não foi possível obter aulas a partir do URI informado.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "URI não informado. Insira um URI válido para visualizar o horário. ::: " + uri);
            }
        });


        //back button to redirect to ImportFilesView
        JButton backBtn = new JButton("Voltar");
        backBtn.addActionListener(e -> viewController.showImportFilesView());

        add(inserirBtn);
        add(verBtn);

        add(backBtn);
        logger.info("Frame da view WebcalScheduleView inicializada com sucesso!");
    }

    public static void setUri(String uri){
        uri = uri;
    }

    public static String getUri(){
        return uri;
    }
}
*/
