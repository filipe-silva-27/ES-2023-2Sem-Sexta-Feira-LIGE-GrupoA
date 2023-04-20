package views;

import controllers.ShowScheduleController;
import controllers.ViewController;
import models.Aula;

import javax.swing.*;
import java.util.List;

public class ShowScheduleView extends View {


    public ShowScheduleView(ViewController viewController) {
        super(viewController);
    }

    @Override
    public void initFrame() {

        this.removeAll();

        JButton verAulas = new JButton("Ver aulas");

        verAulas.addActionListener(e -> {
            // Get the list of events from the controller
            List<Aula> aulas = ((ShowScheduleController) viewController).getAulas();
            new CalendarView(aulas);
        });

        JButton backBtn = new JButton("Voltar");
        backBtn.addActionListener(e -> viewController.showMainMenuView());

        //botao para exportar
        JButton exportBtn = new JButton("Exportar");
        exportBtn.addActionListener(e -> viewController.showExportFilesView());

        add(verAulas);
        add(exportBtn);
        add(backBtn);

    }
}
