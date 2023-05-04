package models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;


import static org.junit.jupiter.api.Assertions.*;

class AulaTest {

    private Aula aula;
    private Aula aula2;
    @BeforeEach
    void setUp() {
        UnidadeCurricular uc = new UnidadeCurricular("LEI", "Programação Orientada a Objetos");
        aula = new Aula(uc, "Manhã", "LEI01", 30, "A101", 40);
        LocalDateTime fixedDate = LocalDateTime.of(2022, Month.JANUARY, 1, 10, 30);
        Date date = Date.from(fixedDate.atZone(ZoneId.systemDefault()).toInstant());
        DataAula dataAula = new DataAula(DiaSemana.MONDAY, LocalTime.of(8,0),LocalTime.of(9,30), date);
        aula.setDataAula(dataAula);
        
        UnidadeCurricular uc2 = new UnidadeCurricular("LP", "Linguagem de Programação");
        aula2 = new Aula(uc2, "Tarde", "LP01", 20, "A102", 50);
        LocalDateTime fixedDate1 = LocalDateTime.of(2022, Month.JANUARY, 1, 10, 30);
        Date date1 = Date.from(fixedDate1.atZone(ZoneId.systemDefault()).toInstant());
        DataAula dataAula2 = new DataAula(DiaSemana.MONDAY, LocalTime.of(10, 0), LocalTime.of(12, 0), date1);
        aula2.setDataAula(dataAula2);
    }
    @Test
    void getUc() {
        UnidadeCurricular uc = new UnidadeCurricular("LEI", "Programação Orientada a Objetos");
        assertEquals(uc, aula.getUc());

    }

    @Test
    void getDataAula() {
        DataAula dataAula = new DataAula(DiaSemana.MONDAY, LocalTime.MIN, LocalTime.MAX, Date.from(Instant.now()));
        assertEquals(dataAula, aula.getDataAula());
    }

    @Test
    void setDataAula() {
        UnidadeCurricular uc = new UnidadeCurricular("LEI", "Programação Orientada a Objetos");
        Aula aula = new Aula(uc, "Manhã", "A", 20, "Sala 101", 30);

        DataAula dataAula = new DataAula(DiaSemana.MONDAY, LocalTime.MIN,LocalTime.MAX, Date.from(Instant.now()));
        aula.setDataAula(dataAula);

        assertEquals(dataAula, aula.getDataAula());
    }

    @Test
    void getTurno() {
        assertEquals("Manhã", aula.getTurno());
    }

    @Test
    void getTurma() {
        assertEquals("LEI01", aula.getTurma());
    }

    @Test
    void getNumInscritos() {
        assertEquals(30, aula.getNumInscritos());
    }

    @Test
    void getSala() {
        assertEquals("A101", aula.getSala());
    }

    @Test
    void getLotacao() {
        assertEquals(40, aula.getLotacao());
    }

    @Test
    void compareTo() {
        assertTrue(aula.compareTo(aula2) < 0);
        assertTrue(aula2.compareTo(aula) > 0);
    }


    @Test
    void testEquals() {
    	Aula aula3 = new Aula(aula.getUc(), "Manhã", "LEI01", 30, "A101", 40);
        LocalDateTime fixedDate = LocalDateTime.of(2022, Month.JANUARY, 1, 10, 30);
        Date date = Date.from(fixedDate.atZone(ZoneId.systemDefault()).toInstant());
        DataAula dataAula3 = new DataAula(DiaSemana.MONDAY, LocalTime.of(8,0),LocalTime.of(9,30), date);
        aula3.setDataAula(dataAula3);
        assertEquals(aula, aula3);
        
        Aula aula4 = null;

        Assertions.assertNotEquals(aula, aula4);
    }

    @Test
    void testHashCode() {
        assertEquals(aula.hashCode(), aula.hashCode());
    }

    @Test
    void testToString() {
    	String expected = "Aula{turno='Manhã', turma='LEI01', numInscritos=30, dataAula=DataAula{data=2022-01-01, horaInicio=08:00, horaFim=09:30}, sala='A101', lotacao=40}";
        String actual = aula.toString();
        assertEquals(expected, actual);
    }
}