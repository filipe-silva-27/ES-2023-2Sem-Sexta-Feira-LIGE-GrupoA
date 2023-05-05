package views;

import controllers.ShowScheduleController;
import controllers.ViewController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import controllers.ShowScheduleController;
import controllers.ViewController;
import models.Aula;
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
import java.util.List;


import javax.swing.*;

public class WebcalScheduleView extends View {

    private static final Logger logger = LoggerFactory.getLogger(views.WebcalScheduleView.class);

    /**
     * Construtor da classe ShowScheduleView.
     *
     * @param viewController O controlador da view.
     */
    public WebcalScheduleView(ViewController viewController) {
        super(viewController);
    }

    /**
     * Inicializa o frame da view.
     */
    @Override
    public void initFrame() {

        this.removeAll();

        JButton verAulas = new JButton("Ver aulas");



        JButton backBtn = new JButton("Voltar");
        backBtn.addActionListener(e -> viewController.showMainMenuView());

        add(verAulas);

        add(backBtn);
    }
}
