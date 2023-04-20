package views;

import controllers.ViewController;

import javax.swing.*;

/**
 * Classe que é a view de visualização do horário
 */
public class CreateScheduleView extends View{

    /**
     * Construtor da classe CreateScheduleView.
     *
     * @param viewController O controlador da view.
     */
    public CreateScheduleView(ViewController viewController){
        super(viewController);
    }

    /**
     * Inicializa o frame da view.
     */
    @Override
    public void initFrame() {
        this.removeAll();
        //TODO implementar criação do horário
        JButton backBtn = new JButton("Voltar");
        backBtn.addActionListener(e -> viewController.showMainMenuView());

        add(backBtn);

    }
}
