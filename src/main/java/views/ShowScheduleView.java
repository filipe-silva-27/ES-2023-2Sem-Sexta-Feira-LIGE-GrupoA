package views;

import controllers.ShowScheduleController;
import controllers.ViewController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;

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
        verAulas.addActionListener(e ->((ShowScheduleController) viewController).createHtmlView());

        JButton verSobreposicoes = new JButton("Ver sobreposições");
        verSobreposicoes.addActionListener(e ->((ShowScheduleController) viewController).showSobreposicoes());

        JButton backBtn = new JButton("Voltar");
        backBtn.addActionListener(e -> viewController.showMainMenuView());

        add(verAulas);
        add(verSobreposicoes);
        add(backBtn);

    }
}
