package controllers;

import gui.App;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Esta classe é um controlador para criar um horário.
 * Estende a classe ViewController.
 */
public class CreateScheduleController extends ViewController{

    private static final Logger logger = LoggerFactory.getLogger(CreateScheduleController.class);

    /**
     * Construtor da classe CreateScheduleController.
     * @param app O objeto principal da aplicação.
     */
    public CreateScheduleController(App app) {
        super(app);
        logger.info("- inicializado com sucesso.");
    }

}