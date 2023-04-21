package models;

import jdk.internal.icu.lang.UCharacterDirection;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class DataAulaTest {

    @Test
    void getDiaSemana() {
        DataAula dataAula = new DataAula(DiaSemana.MONDAY, LocalTime.of(8, 0), LocalTime.of(10, 0), new Date());
        assertEquals(DiaSemana.MONDAY, dataAula.getDiaSemana());
    }

    @Test
    void getHoraInicio() {
        DataAula dataAula = new DataAula(DiaSemana.TUESDAY, LocalTime.of(10, 30), LocalTime.of(12, 30), new Date());
        assertEquals(LocalTime.of(10, 30), dataAula.getHoraInicio());
    }

    @Test
    void getHoraFim() {
        DataAula dataAula = new DataAula(DiaSemana.WEDNESDAY, LocalTime.of(13, 0), LocalTime.of(15, 0), new Date());
        assertEquals(LocalTime.of(15, 0), dataAula.getHoraFim());
    }

    @Test
    void getData() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.APRIL, 21);
        Date date = calendar.getTime();
        DataAula dataAula = new DataAula(DiaSemana.THURSDAY, LocalTime.of(19, 0), LocalTime.of(21, 0), date);
        assertEquals(date, dataAula.getData());
    }

    @Test
    void testEquals() {
        DataAula dataAula1 = new DataAula(DiaSemana.FRIDAY, LocalTime.of(10, 0), LocalTime.of(12, 0), new Date());
        DataAula dataAula2 = new DataAula(DiaSemana.FRIDAY, LocalTime.of(10, 0), LocalTime.of(12, 0), new Date());
        assertEquals(dataAula1, dataAula2);
    }

    @Test
    void testHashCode() {
        DataAula dataAula1 = new DataAula(DiaSemana.MONDAY, LocalTime.of(8, 0), LocalTime.of(10, 0), new Date());
        DataAula dataAula2 = new DataAula(DiaSemana.MONDAY, LocalTime.of(8, 0), LocalTime.of(10, 0), new Date());
        assertEquals(dataAula1.hashCode(), dataAula2.hashCode());
    }

    @Test
    void testToString() {
        DiaSemana diaSemana = DiaSemana.MONDAY;
        LocalTime horaInicio = LocalTime.of(8, 0);
        LocalTime horaFim = LocalTime.of(10, 0);
        Date data = new Date();
        DataAula dataAula = new DataAula(diaSemana, horaInicio, horaFim, data);
        String expected = "DataAula{diaSemana=Monday, horaInicio=08:00, horaFim=10:00, data=" + data.toString() + "}";
        String result = dataAula.toString();

        assertEquals(expected, result);
    }
}