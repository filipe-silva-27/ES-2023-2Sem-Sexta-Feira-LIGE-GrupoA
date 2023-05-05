package controllers;

import gui.App;
import models.UnidadeCurricular;
import models.Aula;
import models.DataAula;
import models.DiaSemana;
import models.Horario;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.util.*;

import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CreateScheduleControllerTest {
	
	CreateScheduleController controller;
	App app;

	@BeforeEach
	void setUp() throws Exception {
		App app = new App();
		controller = new CreateScheduleController(app);
		
	}
	
	@AfterEach
	void tearDown() {
		app = null;
		controller = null;
	}
	
	@Test
	void testSetAndGetSelectedUnits() {
	    Set<UnidadeCurricular> selectedUnits = new HashSet<>();
	    UnidadeCurricular uc1 = new UnidadeCurricular("IGE", "AED");
	    UnidadeCurricular uc2 = new UnidadeCurricular("IGE", "ABC");
	    selectedUnits.add(uc1);
	    selectedUnits.add(uc2);
	    controller.setSelectedUnits(selectedUnits);
	    Assertions.assertEquals(controller.getSelectedUnits(), selectedUnits);
	    Set<UnidadeCurricular> getUnits = controller.getSelectedUnits();
	    Assertions.assertTrue(getUnits.contains(uc1));
	    Assertions.assertTrue(getUnits.contains(uc2));
	}
	
	@Test
	void testSetAndGetSelectedAulas() {
		List<Aula> aulasList = new ArrayList<>();
		UnidadeCurricular uc1 = new UnidadeCurricular("LEI", "Programação Orientada a Objetos");
		Aula aula1 = new Aula(uc1, "Manhã", "LEI01", 30, "A101", 40);
		LocalDateTime fixedDate1 = LocalDateTime.of(2022, Month.MARCH, 14, 8, 0);
		Date date1 = Date.from(fixedDate1.atZone(ZoneId.systemDefault()).toInstant());
		DataAula dataAula1 = new DataAula(DiaSemana.MONDAY, LocalTime.of(8,0),LocalTime.of(9,30), date1);
		aula1.setDataAula(dataAula1);

		UnidadeCurricular uc2 = new UnidadeCurricular("MAT", "Cálculo");
		Aula aula2 = new Aula(uc2, "Tarde", "MAT02", 25, "B201", 30);
		LocalDateTime fixedDate2 = LocalDateTime.of(2022, Month.MARCH, 14, 14, 0);
		Date date2 = Date.from(fixedDate2.atZone(ZoneId.systemDefault()).toInstant());
		DataAula dataAula2 = new DataAula(DiaSemana.MONDAY, LocalTime.of(14,0),LocalTime.of(15,30), date2);
		aula2.setDataAula(dataAula2);
		
		aulasList.add(aula1);
		aulasList.add(aula2);
		controller.setSelectedAulas(aulasList);
		Assertions.assertEquals(controller.getSelectedAulas(), aulasList);
		
	}
	
	@Test
	void testCreateHorario() {
	    List<Aula> aulasList = new ArrayList<>();
	    UnidadeCurricular uc1 = new UnidadeCurricular("LEI", "Programação Orientada a Objetos");
	    Aula aula1 = new Aula(uc1, "Manhã", "LEI01", 30, "A101", 40);
	    LocalDateTime fixedDate1 = LocalDateTime.of(2022, Month.MARCH, 14, 8, 0);
	    Date date1 = Date.from(fixedDate1.atZone(ZoneId.systemDefault()).toInstant());
	    DataAula dataAula1 = new DataAula(DiaSemana.MONDAY, LocalTime.of(8,0),LocalTime.of(9,30), date1);
	    aula1.setDataAula(dataAula1);

	    UnidadeCurricular uc2 = new UnidadeCurricular("MAT", "Cálculo");
	    Aula aula2 = new Aula(uc2, "Tarde", "MAT02", 25, "B201", 30);
	    LocalDateTime fixedDate2 = LocalDateTime.of(2022, Month.MARCH, 14, 14, 0);
	    Date date2 = Date.from(fixedDate2.atZone(ZoneId.systemDefault()).toInstant());
	    DataAula dataAula2 = new DataAula(DiaSemana.MONDAY, LocalTime.of(14,0),LocalTime.of(15,30), date2);
	    aula2.setDataAula(dataAula2);

	    aulasList.add(aula1);
	    aulasList.add(aula2);
	    controller.setSelectedAulas(aulasList);

	    controller.createHorario();

	    // Check that the horario created by the controller contains the same aulas as the list of selected aulas
	    Horario createdHorario = controller.getSelectedHorario();
	    Set<Aula> createdAulas = new HashSet<>();
	    for (UnidadeCurricular uc : createdHorario.getUnidadesCurriculares()) {
	        createdAulas.addAll(uc.getAulas());
	    }
	    Assertions.assertEquals(new HashSet<>(aulasList), createdAulas);
	}
	
	@Test
	void testCreateHorario_emptyList() {
	    controller.setSelectedAulas(new ArrayList<>());
	    controller.createHorario();
	    Assertions.assertNull(controller.getSelectedHorario());
	}



}
