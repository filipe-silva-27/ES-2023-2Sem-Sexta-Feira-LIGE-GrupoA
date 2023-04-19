package controllers;

import gui.App;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConvertController extends ViewController {

    private static final Logger logger = LoggerFactory.getLogger(ConvertController.class);

    public ConvertController(App app) {
        super(app);
        logger.info("- inicializado com sucesso.");
    }

}
