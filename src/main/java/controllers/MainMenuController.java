package controllers;

import gui.App;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainMenuController extends ViewController{

    private static final Logger logger = LoggerFactory.getLogger(MainMenuController.class);

    public MainMenuController(App app) {
        super(app);
        logger.info("- inicializado com sucesso.");
    }


}