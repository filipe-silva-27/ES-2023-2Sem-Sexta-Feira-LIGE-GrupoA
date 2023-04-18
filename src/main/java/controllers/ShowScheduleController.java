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

    public Map<UnidadeCurricular, List<Aula>> getAulas() {
        Map<UnidadeCurricular, List<Aula>> aulasMap = new HashMap<>();
        if (isHorarioSet()) {
            for(UnidadeCurricular uc : getHorario().getUnidadesCurriculares()){
                List<Aula> aulasAux = uc.getAulas();
                Collections.sort(aulasAux);
                aulasMap.put(uc, aulasAux);
            }
        }

        // Debug the map contents
        for (Map.Entry<UnidadeCurricular, List<Aula>> entry : aulasMap.entrySet()) {
            UnidadeCurricular uc = entry.getKey();
            List<Aula> aulas = entry.getValue();
            for (Aula a : aulas) {
                logger.debug("Unidade Curricular: " + uc.getNomeUC() + " " + a.getTurno() + " " + a.getDataAula().toString());
            }
        }

        return aulasMap;
    }



}
