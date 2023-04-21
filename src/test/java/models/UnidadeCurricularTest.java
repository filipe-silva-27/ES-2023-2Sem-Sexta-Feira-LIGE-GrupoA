package models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class UnidadeCurricularTest {

    private UnidadeCurricular uc;
    private Aula aula1;
    private Aula aula2;
    @BeforeEach
    void setUp() {
        uc = new UnidadeCurricular("LIGE", "Engenharia de Software");

        DataAula dataAula1 = new DataAula(DiaSemana.MONDAY, LocalTime.MIN,LocalTime.MAX, Date.from(Instant.now()));
        DataAula dataAula2 = new DataAula(DiaSemana.MONDAY, LocalTime.MIN,LocalTime.MAX, Date.from(Instant.now()));

        aula1 = new Aula(uc, "1", "A", 40, "B302", 50);
        aula1.setDataAula(dataAula1);

        aula2 = new Aula(uc, "2", "B", 30, "B303", 40);
        aula2.setDataAula(dataAula2);

        uc.addAula(aula1);
        uc.addAula(aula2);
    }

    @Test
    void getAulas() {
        List<Aula> aulas = uc.getAulas();
        assertEquals(2, aulas.size());
        assertTrue(aulas.contains(aula1));
        assertTrue(aulas.contains(aula2));
    }

    @Test
    void addAula() {
        DataAula dataAula3 = new DataAula(DiaSemana.MONDAY, LocalTime.MIN,LocalTime.MAX, Date.from(Instant.now()));
        Aula aula3 = new Aula(uc, "1", "C", 20, "B304", 30);
        aula3.setDataAula(dataAula3);
        assertTrue(uc.addAula(aula3));
        assertTrue(uc.getAulas().contains(aula3));
    }

    @Test
    void getCurso() {
        assertEquals("Engenharia de Software", uc.getCurso());
    }

    @Test
    void getNomeUC() {
        assertEquals("Engenharia de Software", uc.getNomeUC());
    }

    @Test
    void testEquals() {
        UnidadeCurricular uc1 = new UnidadeCurricular("Engenharia de Software", "Inteligência Artificial");
        assertEquals(uc, uc1);
    }

    @Test
    void testHashCode() {
        UnidadeCurricular uc1 = new UnidadeCurricular("Engenharia de Software", "Inteligência Artificial");
        assertEquals(uc1.hashCode(), uc.hashCode());
    }


    @Test
    void testToString() {
        String expected = "UnidadeCurricular{curso='Ciência da Computação', nomeUC='Estruturas de Dados', aulas=[Aula{uc=Estruturas de Dados, turno='Manhã', turma='A', numInscritos=20, dataAula=null, sala='Sala 1', lotacao=30}, Aula{uc=Estruturas de Dados, turno='Tarde', turma='B', numInscritos=25, dataAula=null, sala='Sala 2', lotacao=35}]}";
        String result = uc.toString();

        assertEquals(expected, result);
    }
}