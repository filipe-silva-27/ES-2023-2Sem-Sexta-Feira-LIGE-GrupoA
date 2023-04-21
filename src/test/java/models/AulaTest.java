package models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalTime;
import java.util.Date;


import static org.junit.jupiter.api.Assertions.*;

class AulaTest {

    private Aula aula;
    @BeforeEach
    void setUp() {
        UnidadeCurricular uc = new UnidadeCurricular("LEI", "Programação Orientada a Objetos");
        aula = new Aula(uc, "Manhã", "LEI01", 30, "A101", 40);
        DataAula dataAula = new DataAula(DiaSemana.MONDAY, LocalTime.MIN,LocalTime.MAX, Date.from(Instant.now()));
        aula.setDataAula(dataAula);
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
        Aula aula2 = new Aula(aula.getUc(), "Manhã", "LEI01", 30, "A102", 50);
        DataAula dataAula2 = new DataAula(DiaSemana.MONDAY, LocalTime.MIN,LocalTime.MAX, Date.from(Instant.now()));
        aula2.setDataAula(dataAula2);
        assertTrue(aula.compareTo(aula2) < 0);
        assertTrue(aula2.compareTo(aula) > 0);
    }

    @Test
    void testEquals() {
        UnidadeCurricular uc = new UnidadeCurricular("LEI", "Programação Orientada a Objetos");
        Aula aula2 = new Aula(uc, "Manhã", "LEI01", 30, "A101", 40);
        DataAula dataAula2 = new DataAula(DiaSemana.MONDAY, LocalTime.MIN,LocalTime.MAX, Date.from(Instant.now()));
        aula2.setDataAula(dataAula2);
        assertEquals(aula, aula2);
    }

    @Test
    void testHashCode() {
        assertEquals(aula.hashCode(), aula.hashCode());
    }

    @Test
    void testToString() {
        String expected = "Aula{turno='Manhã', turma='A', numInscritos=30, dataAula=DataAula{data=2023-04-21, horaInicio=09:00, horaFim=10:30}, sala='Sala A', lotacao=40}";
        String actual = aula.toString();
        assertEquals(expected, actual);
    }
}