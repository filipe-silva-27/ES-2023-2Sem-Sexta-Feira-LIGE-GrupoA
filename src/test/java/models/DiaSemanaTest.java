package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiaSemanaTest {

    @Test
    void fromName() {
        assertEquals(DiaSemana.MONDAY, DiaSemana.fromName("Seg"));
        assertEquals(DiaSemana.TUESDAY, DiaSemana.fromName("Ter"));
        assertEquals(DiaSemana.WEDNESDAY, DiaSemana.fromName("Qua"));
        assertEquals(DiaSemana.THURSDAY, DiaSemana.fromName("Qui"));
        assertEquals(DiaSemana.FRIDAY, DiaSemana.fromName("Sex"));
        assertEquals(DiaSemana.SATURDAY, DiaSemana.fromName("Sáb"));
        assertEquals(DiaSemana.SUNDAY, DiaSemana.fromName("Dom"));
        assertThrows(IllegalArgumentException.class, () -> DiaSemana.fromName("Invalid"));
    }

    @Test
    void getName() {
        assertEquals("Seg", DiaSemana.MONDAY.getName());
        assertEquals("Ter", DiaSemana.TUESDAY.getName());
        assertEquals("Qua", DiaSemana.WEDNESDAY.getName());
        assertEquals("Qui", DiaSemana.THURSDAY.getName());
        assertEquals("Sex", DiaSemana.FRIDAY.getName());
        assertEquals("Sáb", DiaSemana.SATURDAY.getName());
        assertEquals("Dom", DiaSemana.SUNDAY.getName());
    }

}