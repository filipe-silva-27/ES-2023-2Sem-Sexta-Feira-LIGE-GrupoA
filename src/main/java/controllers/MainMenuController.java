package controllers;

import gui.App;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Esta classe é um controlador para o menu principal.
 * Estende a classe ViewController.
 * @see ViewController
 */
public class MainMenuController extends ViewController{

    private static final Logger logger = LoggerFactory.getLogger(MainMenuController.class);

    /**
     * Construtor da classe MainMenuController.
     * @param app O objeto principal da aplicação.
     * @see App
     */
    public MainMenuController(App app) {
        super(app);
        logger.info("- inicializado com sucesso.");
    }


}