package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AulaTest {

    @Test
    void getUc() {
        UnidadeCurricular uc = new UnidadeCurricular("LIGE", "Eng Soft");
        assertEquals("Eng Soft", uc.getNomeUC());
        assertEquals("LIGE", uc.getCurso());

    }

    @Test
    void getDataAula() {
    }

    @Test
    void setDataAula() {
    }

    @Test
    void getTurno() {
    }

    @Test
    void getTurma() {
    }

    @Test
    void getNumInscritos() {
    }

    @Test
    void getSala() {
    }

    @Test
    void getLotacao() {
    }

    @Test
    void compareTo() {
    }

    @Test
    void testEquals() {
    }

    @Test
    void testHashCode() {
    }

    @Test
    void testToString() {
    }
}