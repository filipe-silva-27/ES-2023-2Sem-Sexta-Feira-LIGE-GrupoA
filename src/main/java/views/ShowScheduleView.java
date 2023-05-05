package views;

import controllers.ShowScheduleController;
import controllers.ViewController;
import models.Aula;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.DetalhesAulasDialog;

import javax.swing.*;
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

        JButton verSobrelotacao = new JButton("Ver sobrelotações");
        verSobrelotacao.addActionListener(e -> {
            List<Aula> aulaList = ((ShowScheduleController) viewController).getAulas();
            List<Aula> aulasSobrepostas = DetalhesAulasDialog.getSobreposicoes(aulaList);
            DetalhesAulasDialog.showSobreposicoesView(aulasSobrepostas);
        });

        JButton verSobreposicoes = new JButton("Ver sobreposições");
        verSobreposicoes.addActionListener(e ->{
            List<Aula> aulaList = ((ShowScheduleController) viewController).getAulas();
            List<Aula> aulasSobrelotadas = DetalhesAulasDialog.getAulasSobreLotadas(aulaList);
            DetalhesAulasDialog.showAulasSobrelotadasView(aulasSobrelotadas);
        });

        JButton backBtn = new JButton("Voltar");
        backBtn.addActionListener(e -> viewController.showMainMenuView());

        add(verAulas);
        add(verSobrelotacao);
        add(verSobreposicoes);
        add(backBtn);

    }
}
