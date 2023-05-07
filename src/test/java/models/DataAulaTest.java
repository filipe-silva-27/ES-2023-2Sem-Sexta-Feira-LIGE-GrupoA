package models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;

class DataAulaTest {
    private DataAula dataAula;

    @BeforeEach
    void setUp() {
        LocalDateTime fixedDate = LocalDateTime.of(2022, Month.JANUARY, 1, 10, 30);
        Date date = Date.from(fixedDate.atZone(ZoneId.systemDefault()).toInstant());
        dataAula = new DataAula(DiaSemana.MONDAY, LocalTime.of(8, 0), LocalTime.of(9, 30), date);
    }

    @Test
    void testGetDiaSemana() {
        Assertions.assertEquals(DiaSemana.MONDAY, dataAula.getDiaSemana());
    }

    @Test
    void testGetHoraInicio() {
        Assertions.assertEquals(LocalTime.of(8, 0), dataAula.getHoraInicio());
    }

    @Test
    void testGetHoraFim() {
        Assertions.assertEquals(LocalTime.of(9, 30), dataAula.getHoraFim());
    }

    @Test
    void testGetData() {
        LocalDateTime fixedDate = LocalDateTime.of(2022, Month.JANUARY, 1, 10, 30);
        Date expected = Date.from(fixedDate.atZone(ZoneId.systemDefault()).toInstant());
        Assertions.assertEquals(expected, dataAula.getData());
    }

    @Test
    void testEquals() {
        LocalDateTime fixedDate = LocalDateTime.of(2022, Month.JANUARY, 1, 10, 30);
        Date date = Date.from(fixedDate.atZone(ZoneId.systemDefault()).toInstant());
        DataAula expected = new DataAula(DiaSemana.MONDAY, LocalTime.of(8, 0), LocalTime.of(9, 30), date);
        Assertions.assertEquals(expected, dataAula);
    }

    @Test
    void testHashCode() {
        LocalDateTime fixedDate = LocalDateTime.of(2022, Month.JANUARY, 1, 10, 30);
        Date date = Date.from(fixedDate.atZone(ZoneId.systemDefault()).toInstant());
        DataAula expected = new DataAula(DiaSemana.MONDAY, LocalTime.of(8, 0), LocalTime.of(9, 30), date);
        Assertions.assertEquals(expected.hashCode(), dataAula.hashCode());
    }

    @Test
    void testToString() {
        String expected = "DataAula{diaSemana=MONDAY, horaInicio=08:00, horaFim=09:30, data=" + dataAula.getData() + "}";
        Assertions.assertEquals(expected, dataAula.toString());
    }
}
