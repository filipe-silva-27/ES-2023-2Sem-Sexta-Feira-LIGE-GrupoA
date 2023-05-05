package views;

import controllers.ViewController;
import org.junit.Ignore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.JButton;

/**
 * Classe que representa a view do menu principal.
 */
public class MainMenuView extends View {

    private static final Logger logger = LoggerFactory.getLogger(MainMenuView.class);

    /**
     * Construtor da classe MainMenuView.
     * @param viewController O controlador da view.
     */
    public MainMenuView(ViewController viewController) {
        super(viewController);

    }

    /**
     * Inicializa o frame da view.
     */
    @Override
    @Ignore
    public void initFrame() {
        this.removeAll();

        logger.info("Inicializando a frame da view MainMenuView");
        JButton criarBtn = new JButton("Criar Horário");
        JButton viewBtn = new JButton("Visualizar Horário");
        JButton convertBtn = new JButton("Converter");

        criarBtn.addActionListener(e -> viewController.showCreateScheduleView());
        viewBtn.addActionListener(e -> viewController.showShowScheduleView());
        convertBtn.addActionListener(e -> viewController.showConvertView());

        //back button to redirect to ImportFilesView
        JButton backBtn = new JButton("Voltar");
        backBtn.addActionListener(e -> viewController.showImportFilesView());

        add(criarBtn);
        add(viewBtn);
        add(convertBtn);
        add(backBtn);
        logger.info("Frame da view MainMenuView inicializada com sucesso!");
    }
}

