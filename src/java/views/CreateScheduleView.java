package views;

import controllers.ViewController;

import javax.swing.*;

/**
 * Classe que é a view de visualização do horário
 */
public class CreateScheduleView extends View{

    public CreateScheduleView(ViewController viewController){
        super(viewController);
    }

    @Override
    public void initFrame() {
        //TODO implementar criação do horário
        JButton backBtn = new JButton("Voltar");
        backBtn.addActionListener(e -> viewController.showMainMenuView());

        add(backBtn);

    }
}
