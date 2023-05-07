package utils;

import models.Aula;
import models.DataAula;
import models.DiaSemana;
import models.UnidadeCurricular;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DetalhesAulasDialogTest {

	List<Aula> aulas;
	@BeforeEach
	void setUp() {
		aulas = new ArrayList<>();
	}

	@AfterEach
	void tearDown() {
	}

	@Test
	void showSobreposicoesViewEmpty() {
		DetalhesAulasDialog.showSobreposicoesView(aulas);
	}

	@Test
	void showSobreposicoesView() {

		Aula aula1 = new Aula(new UnidadeCurricular("IGE" , "POO"),"String turno", "String turma", 50,"String sala", 30);
		Aula aula2 = new Aula(new UnidadeCurricular("IGE" , "PCD"),"turno", "turma", 50,"sala", 30);
		aula1.setDataAula(new DataAula(DiaSemana.MONDAY, LocalTime.of(10, 0), LocalTime.of(11, 0), Date.from(java.time.Instant.now())));
		aula2.setDataAula(new DataAula(DiaSemana.MONDAY, LocalTime.of(11, 0), LocalTime.of(12, 0), Date.from(java.time.Instant.now())));

		aulas.add(aula1);
		aulas.add(aula2);
		DetalhesAulasDialog dialogMock = mock(DetalhesAulasDialog.class);

		DetalhesAulasDialog.showSobreposicoesView(aulas);

	}



	@Test
	void showAulasSobrelotadasViewEmpty() {
		DetalhesAulasDialog.showAulasSobrelotadasView(aulas);
	}

	@Test
	void showAulasSobrelotadasView() {
		Aula aula1 = new Aula(new UnidadeCurricular("IGE" , "POO"),"String turno", "String turma", 50,"String sala", 30);
		Aula aula2 = new Aula(new UnidadeCurricular("IGE" , "PCD"),"turno", "turma", 50,"sala", 30);
		aula1.setDataAula(new DataAula(DiaSemana.MONDAY, LocalTime.of(10, 0), LocalTime.of(11, 0), Date.from(java.time.Instant.now())));
		aula2.setDataAula(new DataAula(DiaSemana.MONDAY, LocalTime.of(11, 0), LocalTime.of(12, 0), Date.from(java.time.Instant.now())));

		aulas.add(aula1);
		aulas.add(aula2);
		DetalhesAulasDialog dialogMock = mock(DetalhesAulasDialog.class);
		DetalhesAulasDialog.showAulasSobrelotadasView(aulas);
	}


	@Test
	void getAulasSobreLotadas() {

		Aula aula1 = new Aula(new UnidadeCurricular("IGE" , "POO"),"String turno", "String turma", 50,"String sala", 30);
		Aula aula2 = new Aula(new UnidadeCurricular("IGE" , "PCD"),"turno", "turma", 50,"sala", 30);
		Aula aula3 = new Aula(new UnidadeCurricular("IGE" , "UC2"),"String turno", "String turma", 10,"String sala", 30);


		aula1.setDataAula(new DataAula(DiaSemana.MONDAY, LocalTime.of(10, 0), LocalTime.of(11, 0), Date.from(java.time.Instant.now())));
		aula2.setDataAula(new DataAula(DiaSemana.MONDAY, LocalTime.of(12, 0), LocalTime.of(13, 0), Date.from(java.time.Instant.now())));
		aula3.setDataAula(new DataAula(DiaSemana.MONDAY, LocalTime.of(14, 0), LocalTime.of(15, 0), Date.from(java.time.Instant.now())));

		aulas.add(aula1);
		aulas.add(aula2);
		aulas.add(aula3);

		List<Aula> expected = new ArrayList<>();
		expected.add(aula1);
		expected.add(aula2);

		DetalhesAulasDialog.getAulasSobreLotadas(aulas);
		assertEquals(expected, DetalhesAulasDialog.getAulasSobreLotadas(aulas), "Incorrect array aulasSobrelotadas");


	}

	@Test
	void getSobreposicoes() {

		Aula aula1 = new Aula(new UnidadeCurricular("IGE" , "POO"),"String turno", "String turma", 20,"String sala", 30);
		Aula aula2 = new Aula(new UnidadeCurricular("IGE" , "PCD"),"turno", "turma", 20,"sala", 30);
		Aula aula3 = new Aula(new UnidadeCurricular("IGE" , "UC2"),"String turno", "String turma", 20,"String sala", 30);


		aula1.setDataAula(new DataAula(DiaSemana.MONDAY, LocalTime.of(10, 0), LocalTime.of(11, 0), Date.from(java.time.Instant.now())));
		aula2.setDataAula(new DataAula(DiaSemana.MONDAY, LocalTime.of(12, 0), LocalTime.of(13, 0), Date.from(java.time.Instant.now())));
		aula3.setDataAula(new DataAula(DiaSemana.MONDAY, LocalTime.of(10, 0), LocalTime.of(11, 0), Date.from(java.time.Instant.now())));

		aulas.add(aula1);
		aulas.add(aula2);
		aulas.add(aula3);

		List<Aula> expected = new ArrayList<>();
		expected.add(aula1);
		expected.add(aula3);

		DetalhesAulasDialog.getSobreposicoes(aulas);
		assertEquals(expected, DetalhesAulasDialog.getSobreposicoes(aulas), "Incorrect array aulasSobrelotadas");

	}
}