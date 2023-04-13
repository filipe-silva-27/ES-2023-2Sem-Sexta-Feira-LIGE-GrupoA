package views;

import gui.ViewController;

import javax.swing.*;

import static gui.App.*;

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
        backBtn.addActionListener(e -> viewController.getApp().getControllers().get(MAIN_MENU).showView());

        add(backBtn);

    }
}
