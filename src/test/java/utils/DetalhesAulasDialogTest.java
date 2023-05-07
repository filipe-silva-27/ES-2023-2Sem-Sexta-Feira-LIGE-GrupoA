package utils;
import models.Aula;
import models.DataAula;
import models.DiaSemana;
import models.UnidadeCurricular;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DetalhesAulasDialogTest {
	
	

	@Test
	void testGetSobreposicoes() {
		List<Aula> aulasList = new ArrayList<>();
		UnidadeCurricular uc1 = new UnidadeCurricular("LEI", "Programação Orientada a Objetos");
		Aula aula1 = new Aula(uc1, "Manhã", "LEI01", 30, "A101", 40);
		LocalDateTime fixedDate1 = LocalDateTime.of(2022, Month.MARCH, 14, 8, 0);
		Date date1 = Date.from(fixedDate1.atZone(ZoneId.systemDefault()).toInstant());
		DataAula dataAula1 = new DataAula(DiaSemana.MONDAY, LocalTime.of(8,0),LocalTime.of(9,30), date1);
		aula1.setDataAula(dataAula1);

		UnidadeCurricular uc2 = new UnidadeCurricular("MAT", "Cálculo");
		Aula aula2 = new Aula(uc2, "Tarde", "MAT02", 25, "B201", 30);
		LocalDateTime fixedDate2 = LocalDateTime.of(2022, Month.MARCH, 14, 8, 0);
		Date date2 = Date.from(fixedDate2.atZone(ZoneId.systemDefault()).toInstant());
		DataAula dataAula2 = new DataAula(DiaSemana.MONDAY, LocalTime.of(8,0),LocalTime.of(9,30), date2);
		aula2.setDataAula(dataAula2);
		
		aulasList.add(aula1);
		aulasList.add(aula2);

	    List<Aula> sobrepostas = DetalhesAulasDialog.getSobreposicoes(aulasList);
	    assertEquals(2, sobrepostas.size());
	}


	@Test
	void testGetSobreposicoes_EmptyList() {
	    List<Aula> sobrepostas = DetalhesAulasDialog.getSobreposicoes(new ArrayList<>());
	    assertNotNull(sobrepostas);
	    assertEquals(0, sobrepostas.size());
	}

	@Test
	void testGetAulasSobreLotadas() {
	    List<Aula> aulasList = new ArrayList<>();
		UnidadeCurricular uc1 = new UnidadeCurricular("LEI", "Programação Orientada a Objetos");
		Aula aula1 = new Aula(uc1, "Manhã", "LEI01", 50, "A101", 40);
		LocalDateTime fixedDate1 = LocalDateTime.of(2022, Month.MARCH, 14, 8, 0);
		Date date1 = Date.from(fixedDate1.atZone(ZoneId.systemDefault()).toInstant());
		DataAula dataAula1 = new DataAula(DiaSemana.MONDAY, LocalTime.of(8,0),LocalTime.of(9,30), date1);
		aula1.setDataAula(dataAula1);

		UnidadeCurricular uc2 = new UnidadeCurricular("MAT", "Cálculo");
		Aula aula2 = new Aula(uc2, "Tarde", "MAT02", 40, "B201", 30);
		LocalDateTime fixedDate2 = LocalDateTime.of(2022, Month.MARCH, 14, 14, 0);
		Date date2 = Date.from(fixedDate2.atZone(ZoneId.systemDefault()).toInstant());
		DataAula dataAula2 = new DataAula(DiaSemana.MONDAY, LocalTime.of(14,0),LocalTime.of(15,30), date2);
		aula2.setDataAula(dataAula2);
		
		aulasList.add(aula1);
		aulasList.add(aula2);
	    List<Aula> sobrelotadas = DetalhesAulasDialog.getAulasSobreLotadas(aulasList);
	    assertEquals(2, sobrelotadas.size());
	}


	@Test
	void testGetAulasSobreLotadas_EmptyList() {
	    List<Aula> sobrelotadas = DetalhesAulasDialog.getAulasSobreLotadas(new ArrayList<>());
	    assertNotNull(sobrelotadas);
	    assertEquals(0, sobrelotadas.size());
	}


}
