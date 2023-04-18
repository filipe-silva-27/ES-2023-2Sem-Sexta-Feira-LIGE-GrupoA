package views;

import controllers.ConvertController;
import controllers.ShowScheduleController;
import controllers.UploadFilesController;
import controllers.ViewController;
import models.Aula;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.util.Collections;
import java.util.Set;

public class ShowScheduleView extends View{
    private static final Logger logger = LoggerFactory.getLogger(ShowScheduleView.class);

    public ShowScheduleView(ViewController viewController) {
        super(viewController);
    }

    @Override
    public void initFrame() {
        //TODO implementar a visualização do horário

        JButton showAulas = new JButton("Ver aulas");
        showAulas.addActionListener(e -> ((ShowScheduleController) viewController).getAulas());

        JButton backBtn = new JButton("Voltar");
        backBtn.addActionListener(e -> viewController.showMainMenuView());

        //botao para exportar
        JButton exportBtn = new JButton("Exportar");
        exportBtn.addActionListener(e -> viewController.exportSchedule());

        add(showAulas);
        add(exportBtn);
        add(backBtn);
    }



}
