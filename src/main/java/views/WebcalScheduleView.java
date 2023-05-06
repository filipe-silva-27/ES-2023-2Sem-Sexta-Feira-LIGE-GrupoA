/*
package views;

import controllers.*;
import models.Horario;
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
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.TimerTask;
import java.util.Timer;


import javax.swing.*;

public class WebcalScheduleView extends View {

    private static final Logger logger = LoggerFactory.getLogger(WebcalScheduleView.class);

    */
/**
     * Construtor da classe ShowScheduleView.
     *
     * @param viewController O controlador da view.
     *//*

    public WebcalScheduleView(ViewController viewController) {

        super(viewController);
    }



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
        verBtn.addActionListener(e ->
                WebcalScheduleController.createHtmlView(((WebcalScheduleController) viewController).getAulas()));

       /* verBtn.addActionListener(
                e -> ((WebcalScheduleController) viewController).createHtml());
*/
        //back button to redirect to ImportFilesView
        JButton backBtn = new JButton("Voltar");
        backBtn.addActionListener(e -> viewController.showImportFilesView());
/*verHorario.addActionListener(e ->{
                    List<Aula> selectedAulas = ((CreateScheduleController) viewController).getSelectedAulas();
                    ShowScheduleController.createHtmlView(selectedAulas);
                }*/

        add(inserirBtn);
        add(verBtn);

        add(backBtn);
        logger.info("Frame da view WebcalScheduleView inicializada com sucesso!");
    }
}
*/
