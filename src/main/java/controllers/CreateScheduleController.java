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

    /**
     * Define o conjunto de unidades curriculares selecionadas.
     * @param selectedUnits o conjunto de unidades curriculares selecionadas
     */
    public void setSelectedUnits(Set<UnidadeCurricular> selectedUnits){
        this.selectedUnits = selectedUnits;
        selectedUnits.forEach(f -> logger.info(f.getNomeUC()));
    }

    /**
     * Define a lista de aulas selecionadas.
     * @param selectedAulas a lista de aulas selecionadas
     */
    public void setSelectedAulas(List<Aula> selectedAulas){
        this.selectedAulas = selectedAulas;
        selectedAulas.forEach(f -> logger.info(f.toString()));
    }

    /**
     * Cria um horário com base nas aulas selecionadas.
     * @throws IllegalStateException se nenhuma aula tiver sido selecionada
     */
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
            }else{
                // Adicionar às aulas
                selectedHorario.getUnidadeCurricular(uc).addAula(aula);
            }
            logger.info(aula.getUc().toString());
        }
    }

    /**
     * Obtém o horário selecionado.
     * @return o horário selecionado
     */
    public Horario getSelectedHorario(){
        return selectedHorario;
    }

    /**
     * Obtém a lista de aulas selecionadas.
     * @return a lista de aulas selecionadas
     */
    public List<Aula> getSelectedAulas(){
        return selectedAulas;
    }

    /**
     * Obtém o conjunto de unidades curriculares selecionadas.
     * @return o conjunto de unidades curriculares selecionadas
     */
    public Set<UnidadeCurricular> getSelectedUnits() {
        return selectedUnits;
    }
}