package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CursoTest {

    @Test
    void getNome() {
        Curso curso = new Curso("Engenharia de Software");
        assertEquals("Engenharia de Software", curso.getNome());
    }

    @Test
    void setNome() {
        Curso curso = new Curso("Engenharia de Software");
        curso.setNome("Ciência da Computação");
        assertEquals("Ciência da Computação", curso.getNome());
    }

    @Test
    void testToString() {
        Curso curso = new Curso("Engenharia de Software");
        assertEquals("Curso{nome='Engenharia de Software'}", curso.toString());
    }
}