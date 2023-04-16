package controllers;

import gui.App;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShowScheduleController extends ViewController{

    private static final Logger logger = LoggerFactory.getLogger(ShowScheduleController.class);

    /**
     * @param app - Aplicação que será comum a todos os controladores
     */
    public ShowScheduleController(App app) {
        super(app);
        logger.info("- inicializado com sucesso.");
    }

}
