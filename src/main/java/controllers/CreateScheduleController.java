package controllers;

import gui.App;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateScheduleController extends ViewController{

    private static final Logger logger = LoggerFactory.getLogger(CreateScheduleController.class);
    public CreateScheduleController(App app) {
        super(app);
        logger.info("- inicializado com sucesso.");
    }

}
