package views;

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

/**
 * Classe que representa a view de visualização do horário.
 */
public class ShowScheduleView extends View {

    private static final Logger logger = LoggerFactory.getLogger(ShowScheduleView.class);

    /**
     * Construtor da classe ShowScheduleView.
     * @param viewController O controlador da view.
     */
    public ShowScheduleView(ViewController viewController) {
        super(viewController);
    }

    /**
     * Inicializa o frame da view.
     */
    @Override
    public void initFrame() {

        this.removeAll();

        JButton verAulas = new JButton("Ver aulas");

        verAulas.addActionListener(e ->
                ShowScheduleController.createHtmlView(((ShowScheduleController) viewController).getAulas()));

        JButton verSobrelotacao = new JButton("Ver sobreposições");
        verSobrelotacao.addActionListener(e ->((ShowScheduleController) viewController).showAulasSobreLotadas());

        JButton backBtn = new JButton("Voltar");
        backBtn.addActionListener(e -> viewController.showMainMenuView());

        add(verAulas);
        add(verSobrelotacao);
        add(backBtn);

    }
}
