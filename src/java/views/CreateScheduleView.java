package views;

import gui.ViewController;

import javax.swing.*;

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
