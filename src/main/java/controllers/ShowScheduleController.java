package controllers;

import gui.App;
import models.Aula;
import models.UnidadeCurricular;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class ShowScheduleController extends ViewController{

    private static final Logger logger = LoggerFactory.getLogger(ShowScheduleController.class);

    /**
     * @param app - Aplicação que será comum a todos os controladores
     */
    public ShowScheduleController(App app) {
        super(app);
        logger.info("- inicializado com sucesso.");
    }

    public List<Aula> getAulas() {
        List<Aula> aulaList = new ArrayList<>();
        if (isHorarioSet()) {
            for(UnidadeCurricular uc : getHorario().getUnidadesCurriculares()){
                List<Aula> aulasAux = uc.getAulas();
                aulaList.addAll(aulasAux);
            }
        }
        Collections.sort(aulaList);
//        for(Aula a : aulaList){
//            logger.debug("UC: {} |  {}", a.getUc().getNomeUC(), a.getDataAula());
//        }
        logger.info("Aulas size: {}", aulaList.size());
        return aulaList;
    }



}
