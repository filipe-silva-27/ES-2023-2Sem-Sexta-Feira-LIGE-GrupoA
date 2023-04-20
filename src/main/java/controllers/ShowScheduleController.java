package controllers;

import gui.App;
import models.Aula;
import models.UnidadeCurricular;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Esta classe é um controlador para exibir o horário de aulas.
 * Estende a classe ViewController.
 */
public class ShowScheduleController extends ViewController{

    private static final Logger logger = LoggerFactory.getLogger(ShowScheduleController.class);

    /**
     * Construtor da classe ShowScheduleController.
     * @param app A aplicação principal que será compartilhada por todos os controladores.
     */
    public ShowScheduleController(App app) {
        super(app);
        logger.info("- inicializado com sucesso.");
    }

    /**
     * Obtém a lista de aulas do horário de aulas atual.
     * @return A lista de aulas ordenada.
     */
    public List<Aula> getAulas() {
        List<Aula> aulaList = new ArrayList<>();
        if (isHorarioSet()) {
            for(UnidadeCurricular uc : getHorario().getUnidadesCurriculares()){
                List<Aula> aulasAux = uc.getAulas();
                aulaList.addAll(aulasAux);
            }
        }
        Collections.sort(aulaList);
        logger.info("Aulas size: {}", aulaList.size());
        return aulaList;
    }



}
