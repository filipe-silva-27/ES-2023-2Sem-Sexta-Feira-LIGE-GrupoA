package controllers;

import gui.App;
import models.Aula;
import models.UnidadeCurricular;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

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
            UnidadeCurricular fac = getHorario().getUnidadeCurricularByNome("Fundamentos de Arquitectura de Computadores");
            List<Aula> aulas = fac.getAulas();
            Collections.sort(aulas);
            for(Aula a : aulas){
                logger.debug(String.valueOf(a.getDataAula()));
            }
        }
    }

}
