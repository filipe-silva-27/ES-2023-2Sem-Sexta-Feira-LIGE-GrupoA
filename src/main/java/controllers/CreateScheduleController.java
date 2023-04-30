package controllers;

import gui.App;
import models.Aula;
import models.UnidadeCurricular;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Set;

/**
 * Esta classe é um controlador para criar um horário.
 * Estende a classe ViewController.
 */
public class CreateScheduleController extends ViewController{

    private static final Logger logger = LoggerFactory.getLogger(CreateScheduleController.class);
    private Set<UnidadeCurricular> selectedUnits;
    private List<Aula> selectedAulas;

    /**
     * Construtor da classe CreateScheduleController.
     * @param app O objeto principal da aplicação.
     */
    public CreateScheduleController(App app) {
        super(app);
        logger.info("- inicializado com sucesso.");
    }

    public void setSelectedUnits(Set<UnidadeCurricular> selectedUnits){
        this.selectedUnits = selectedUnits;
        selectedUnits.forEach(f -> logger.info(f.getNomeUC()));
    }

    public void setSelectedAulas(List<Aula> selectedAulas){
        this.selectedAulas = selectedAulas;
        selectedAulas.forEach(f -> logger.info(f.toString()));
    }

    public Set<UnidadeCurricular> getSelectedUnits() {
        return selectedUnits;
    }
}