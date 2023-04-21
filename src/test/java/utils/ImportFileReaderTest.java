package utils;

import models.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.InjectMocks;

import java.io.IOException;
import java.time.LocalTime;
import java.util.Date;

import static org.mockito.Mockito.*;

class ImportFileReaderTest {

    @InjectMocks
    ImportFileReader importFileReader;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCriaDataAula() {
        DataAula expectedDataAula = new DataAula(
                DiaSemana.MONDAY,
                LocalTime.of(8, 30),
                LocalTime.of(10, 0),
                new Date(123456789)
        );
        DataAula actualDataAula = importFileReader.criaDataAula(
                "MONDAY", "08:30", "10:00", "01/01/1970"
        );
        Assertions.assertEquals(expectedDataAula, actualDataAula);
    }

    @Test
    void testCriaUC() {
        UnidadeCurricular expectedUC = new UnidadeCurricular("LEIC", "POO");
        Horario horario = mock(Horario.class);
        horario.addUnidadeCurricular(expectedUC);
        when(horario.getUnidadeCurricular(expectedUC)).thenReturn(expectedUC);

        UnidadeCurricular actualUC = importFileReader.criaUC("LEIC", "POO");
        Assertions.assertEquals(expectedUC, actualUC);

        verify(horario, times(1)).addUnidadeCurricular(expectedUC);
        verify(horario, times(1)).getUnidadeCurricular(expectedUC);
    }

    @Test
    void testCriaHorario() {
        UnidadeCurricular uc = new UnidadeCurricular("LEIC", "POO");
        Aula expectedAula = new Aula(
                uc, "P", "1", 50, "LT1", 100
        );
        expectedAula.setDataAula(new DataAula(
                DiaSemana.MONDAY,
                LocalTime.of(8, 30),
                LocalTime.of(10, 0),
                new Date(123456789)
        ));
        uc.addAula(expectedAula);
        when(importFileReader.criaUC("LEIC", "POO")).thenReturn(uc);


        /*importFileReader.criaHorario(
                "POO", "LEIC", "P", "1", "MONDAY", "08:30", "10:00", "01/01/1970", "LT1", 50, 100
        );
        UnidadeCurricular actualUC = horario.getUnidadeCurricular(uc);
        Assertions.assertEquals(uc, actualUC);

        Aula actualAula = actualUC.getAulas().get(0);
        Assertions.assertEquals(expectedAula, actualAula);*/

        verify(importFileReader, times(1)).criaUC("LEIC", "POO");
    }

    @Test
    void testProcessRecord() throws IOException {
        String[] record = {
                "POO", "LEIC", "P", "1", "MONDAY", "08:30", "10:00", "01/01/1970", "LT1", "50", "100"
        };
        UnidadeCurricular uc = new UnidadeCurricular("LEIC", "POO");
        Aula expectedAula = new Aula(
                uc, "P", "1", 50, "LT1", 100
        );
        expectedAula.setDataAula(new DataAula(
                DiaSemana.MONDAY,
                LocalTime.of(8, 30),
                LocalTime.of(10, 0),
                new Date(123456789)
        ));
        uc.addAula(expectedAula);
        when(importFileReader.criaUC("LEIC", "POO")).thenReturn(uc);

        //importFileReader.processRecord(record);
        Horario horario = mock(Horario.class);
        UnidadeCurricular actualUC = horario.getUnidadeCurricular(uc);
        Assertions.assertEquals(uc, actualUC);

        Aula actualAula = actualUC.getAulas().get(0);
        Assertions.assertEquals(expectedAula, actualAula);

        verify(importFileReader, times(1)).criaUC("LEIC", "POO");
    }

}

