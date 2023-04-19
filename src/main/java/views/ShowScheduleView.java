package views;

import controllers.ShowScheduleController;
import controllers.ViewController;

import javax.swing.*;

public class ShowScheduleView extends View{

    public ShowScheduleView(ViewController viewController) {
        super(viewController);
    }

    @Override
    public void initFrame() {

        JButton verAulas = new JButton("Ver aulas");

        verAulas.addActionListener(e -> ((ShowScheduleController) viewController).getAulas());

        JButton backBtn = new JButton("Voltar");
        backBtn.addActionListener(e -> viewController.showMainMenuView());

        //botao para exportar
        JButton exportBtn = new JButton("Exportar");
        exportBtn.addActionListener(e -> viewController.exportSchedule());

        add(verAulas);
        add(exportBtn);
        add(backBtn);
    }

}
