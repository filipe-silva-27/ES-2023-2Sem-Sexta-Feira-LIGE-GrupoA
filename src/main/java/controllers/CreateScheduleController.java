package controllers;

import gui.App;
import models.Aula;
import models.Horario;
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
    private Horario selectedHorario;

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

    public void createHorario(){
        if (selectedAulas.isEmpty()){
            return;
        }
        selectedHorario = new Horario("Personalizado");
        for (Aula aula: selectedAulas) {
            UnidadeCurricular uc = new UnidadeCurricular(aula.getUc().getCurso(), aula.getUc().getNomeUC());
            if(selectedHorario.addUnidadeCurricular(uc)){
                // Adicionar às aulas
                uc.addAula(aula);
            }
            logger.info(aula.getUc().toString());
            //selectedHorario.addUnidadeCurricular(aula.getUc());
        }
    }

    public Horario getSelectedHorario(){
        return selectedHorario;
    }

    public List<Aula> getSelectedAulas(){
        return selectedAulas;
    }

    public Set<UnidadeCurricular> getSelectedUnits() {
        return selectedUnits;
    }
}