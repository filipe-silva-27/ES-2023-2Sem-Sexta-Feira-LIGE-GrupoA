package controllers;

import gui.App;
import models.Aula;
import models.Turno;
import models.UnidadeCurricular;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

public class ShowScheduleController extends ViewController{

    private static final Logger logger = LoggerFactory.getLogger(ShowScheduleController.class);

    /**
     * @param app - Aplicação que será comum a todos os controladores
     */
    public ShowScheduleController(App app) {
        super(app);
        logger.info("- inicializado com sucesso.");
    }

    public void getAulas(){
        if(isHorarioSet()){
            UnidadeCurricular fac = getHorario().getUnidadeCurricularPorNome("Fundamentos de Arquitectura de Computadores");
            logger.debug(fac.toString());
            Turno t1 = fac.getTurnoPorNome("L0705T06");
            logger.debug(t1.toString());
            Set<Aula> aulas = t1.getAulas();
            logger.debug(aulas.toString());
        }
    }

}
