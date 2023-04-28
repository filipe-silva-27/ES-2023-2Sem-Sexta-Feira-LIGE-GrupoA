package models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CursoTest {

    private Curso curso;

    @BeforeEach
    void setUp() {
        curso = new Curso("Engenharia Informática");
    }

    @Test
    void testGetNome() {
        Assertions.assertEquals("Engenharia Informática", curso.getNome());
    }

    @Test
    void testSetNome() {
        curso.setNome("Engenharia de Software");
        Assertions.assertEquals("Engenharia de Software", curso.getNome());
    }

    @Test
    void testToString() {
        Assertions.assertEquals("Curso{nome='Engenharia Informática'}", curso.toString());
    }
}
