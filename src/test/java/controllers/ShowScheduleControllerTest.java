package controllers;

import gui.App;
import models.Aula;
import models.Horario;
import models.UnidadeCurricular;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShowScheduleControllerTest {

    private ShowScheduleController controller;
    private App app;
    private Horario horario;
    private UnidadeCurricular uc1;
    private UnidadeCurricular uc2;
    private Aula aula1;
    private Aula aula2;
    private Aula aula3;

    @BeforeEach
    void setUp() {
        app = Mockito.mock(App.class);
        controller = new ShowScheduleController(app);

        // Cria um horário de aulas
        horario = new Horario("Calendário");

        // Cria algumas unidades curriculares
        uc1 = new UnidadeCurricular("LIGE", "Engenharia de Software");
        uc2 = new UnidadeCurricular("LEI", "Inteligência Artificial");

        // Cria algumas aulas
        aula1 = new Aula(uc1, "1", "C", 50, "D102", 80);
        aula2 = new Aula(uc2, "3", "B", 20, "C507", 80);
        aula3 = new Aula(uc1, "1", "C", 30, "E02", 80);

        // Adiciona as aulas às unidades curriculares
        uc1.addAula(aula1);
        uc1.addAula(aula3);
        uc2.addAula(aula2);

        // Adiciona as unidades curriculares ao horário
        horario.addUnidadeCurricular(uc1);
        horario.addUnidadeCurricular(uc2);
    }

    @Test
    void getAulas() {
        // Define o horário atual do controlador
        controller.setHorario(horario);

        // Obtém a lista de aulas do horário
        List<Aula> aulas = controller.getAulas();

        // Verifica se as aulas foram ordenadas corretamente
        Assertions.assertEquals(3, aulas.size());
        Assertions.assertEquals(aula1, aulas.get(0));
        Assertions.assertEquals(aula2, aulas.get(1));
        Assertions.assertEquals(aula3, aulas.get(2));
    }
}