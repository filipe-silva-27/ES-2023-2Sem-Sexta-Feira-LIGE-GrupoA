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

    private static final Logger logger = LoggerFactory.getLogger(CreateScheduleController.class);   // Log4j2
    private Set<UnidadeCurricular> selectedUnits; //Set de unidades curriculares escolhida pelo User
    private List<Aula> selectedAulas; //Lista de aulas selecionadas pelo User
    private Horario selectedHorario; //Objeto Horario Novo personalizado pelo User

    /**
     * Construtor da classe CreateScheduleController.
     * @param app O objeto principal da aplicação.
     */
    public CreateScheduleController(App app) {
        super(app);
        logger.info("- inicializado com sucesso.");
    }

    /**
     * Função que faz set do Set de unidades curriculares
     * @param selectedUnits set de UnidadeCurricular dado
     * @see UnidadeCurricular
     */
    public void setSelectedUnits(Set<UnidadeCurricular> selectedUnits){
        this.selectedUnits = selectedUnits;
        selectedUnits.forEach(f -> logger.info(f.getNomeUC()));
    }

    /**
     * Função que faz set da List de aulas
     * @param selectedAulas lista de aulas selecionadas pelo User
     * @see Aula
     */
    public void setSelectedAulas(List<Aula> selectedAulas){
        this.selectedAulas = selectedAulas;
        selectedAulas.forEach(f -> logger.info(f.toString()));
    }

    /**
     * Função que cria o objeto Horário dos turnos escolhidos pelo Utilizador
     * @see Horario
     * @see UnidadeCurricular
     * @see Aula
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
        }
    }


    /**
     * Função getter do Horario criado pelo user
     * @return selectedHorario horario criado pelo user
     * @see Horario
     */
    public Horario getSelectedHorario(){
        return selectedHorario;
    }

    /**
     * Função getter da lista de aulas selecionadas pelo user
     * @return selectedAulas aulas selecionadas pelo user
     * @see Aula
     */
    public List<Aula> getSelectedAulas(){
        return selectedAulas;
    }

    /**
     * Função getter do set de unidades curriculares selecionadas pelo user
     * @return selectedUnits set de unidades curriculares escolhidas pelo user
     * @see UnidadeCurricular
     */
    public Set<UnidadeCurricular> getSelectedUnits() {
        return selectedUnits;
    }
}