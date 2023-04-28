package controllers;

import gui.App;
import models.Aula;
import models.DataAula;
import models.DiaSemana;
import models.Horario;
import models.UnidadeCurricular;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.Instant;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Date;
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
        app = new App();
        controller = new ShowScheduleController(app);

    }
    
    @Test
    void testExportAulasToJson() {
    	
    	// Cria algumas unidades curriculares
        uc1 = new UnidadeCurricular("LIGE", "Engenharia de Software");
        uc2 = new UnidadeCurricular("LEI", "Inteligência Artificial");
        
       

        // Cria algumas aulas
        aula1 = new Aula(uc1, "1", "C", 50, "D102", 80);
        aula2 = new Aula(uc2, "3", "B", 20, "C507", 80);
        
        // Cria data aulas
        Date date1 = new Date();
        Date date2 = new Date(date1.getTime() + 3600 * 1000);

        DataAula dataAula1 = new DataAula(DiaSemana.MONDAY, LocalTime.of(8, 0), LocalTime.of(10, 0), date1);
        DataAula dataAula2 = new DataAula(DiaSemana.MONDAY, LocalTime.of(10, 0), LocalTime.of(12, 0), date2);


        aula1.setDataAula(dataAula1);
        aula2.setDataAula(dataAula2);
        

  
        List<Aula> aulaList = Arrays.asList(aula1, aula2);

        
        String json = controller.exportAulasToJson(aulaList);

    }

    

    @Test
    void testGetAulas() {
        // Cria um horário de aulas
        horario = new Horario("Calendário");

        // Cria algumas unidades curriculares
        uc1 = new UnidadeCurricular("LIGE", "Engenharia de Software");
        uc2 = new UnidadeCurricular("LEI", "Inteligência Artificial");
        
       

        // Cria algumas aulas
        aula1 = new Aula(uc1, "1", "C", 50, "D102", 80);
        aula2 = new Aula(uc2, "3", "B", 20, "C507", 80);
        aula3 = new Aula(uc1, "1", "C", 30, "E02", 80);
        
        // Cria data aulas
        Date date1 = new Date();
        Date date2 = new Date(date1.getTime() + 3600 * 1000); // add an hour
        Date date3 = new Date(date2.getTime() + 3600 * 1000); // add another hour

        DataAula dataAula1 = new DataAula(DiaSemana.MONDAY, LocalTime.of(8, 0), LocalTime.of(10, 0), date1);
        DataAula dataAula2 = new DataAula(DiaSemana.MONDAY, LocalTime.of(10, 0), LocalTime.of(12, 0), date2);
        DataAula dataAula3 = new DataAula(DiaSemana.MONDAY, LocalTime.of(14, 0), LocalTime.of(16, 0), date3);


        aula1.setDataAula(dataAula1);
        aula2.setDataAula(dataAula2);
        aula3.setDataAula(dataAula3);

        // Adiciona as aulas às unidades curriculares
        uc1.addAula(aula1);
        uc1.addAula(aula3);
        uc2.addAula(aula2);

        // Adiciona as unidades curriculares ao horário
        horario.addUnidadeCurricular(uc1);
        horario.addUnidadeCurricular(uc2);
        
        // Define o horário atual do controlador
        controller.setHorario(horario);

        // Obtém a lista de aulas do horário
        List<Aula> aulas = controller.getAulas();

        // Verifica se as aulas foram ordenadas corretamente
        Assertions.assertEquals(3, aulas.size());
        Assertions.assertEquals(aula1, aulas.get(0));
        Assertions.assertEquals(aula2, aulas.get(1));
        Assertions.assertEquals(aula3, aulas.get(2));
        
        controller.setHorario(null);
        List<Aula> aulas1 = controller.getAulas();
        Assertions.assertNotNull(aulas1);
        Assertions.assertEquals(0, aulas1.size());
    }
}