package utils.exporter;

import models.Aula;
import models.DataAula;
import models.DiaSemana;
import models.Horario;
import models.UnidadeCurricular;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class FileExporterTest {

    @Test
    void testHorarioToCsv() {
        Horario horario = new Horario("");

        UnidadeCurricular uc = new UnidadeCurricular("LEI", "Programação Orientada a Objetos");
        Aula aula = new Aula(uc, "Manhã", "LEI01", 30, "A101", 40);
        LocalDateTime fixedDate = LocalDateTime.of(2022, Month.JANUARY, 1, 10, 30);
        Date date = Date.from(fixedDate.atZone(ZoneId.systemDefault()).toInstant());
        DataAula dataAula = new DataAula(DiaSemana.MONDAY, LocalTime.of(8,0),LocalTime.of(9,30), date);
        aula.setDataAula(dataAula);
       
        uc.addAula(aula);

        List<UnidadeCurricular> ucs = new ArrayList<>();
        ucs.add(uc);

        horario.addUnidadeCurricular(uc);

        String csvData = FileExporter.horarioToCsv(horario);
        Assertions.assertNotNull(csvData);
        Assertions.assertTrue(csvData.contains("LEI"));
        Assertions.assertTrue(csvData.contains("Programação Orientada a Objetos"));
        Assertions.assertTrue(csvData.contains("LEI01"));
        Assertions.assertTrue(csvData.contains("Manhã"));
        Assertions.assertTrue(csvData.contains("30"));
        Assertions.assertTrue(csvData.contains("Seg"));
        Assertions.assertTrue(csvData.contains("08:00:00"));
        Assertions.assertTrue(csvData.contains("09:30:00"));
        Assertions.assertTrue(csvData.contains("01/01/2022"));
        Assertions.assertTrue(csvData.contains("A101"));
        Assertions.assertTrue(csvData.contains("40"));
    }

    @Test
    void testHorarioToJson() {
    	Horario horario = new Horario("");

        UnidadeCurricular uc = new UnidadeCurricular("LEI", "Programação Orientada a Objetos");
        Aula aula = new Aula(uc, "Manhã", "LEI01", 30, "A101", 40);
        LocalDateTime fixedDate = LocalDateTime.of(2022, Month.JANUARY, 1, 10, 30);
        Date date = Date.from(fixedDate.atZone(ZoneId.systemDefault()).toInstant());
        DataAula dataAula = new DataAula(DiaSemana.MONDAY, LocalTime.of(8,0),LocalTime.of(9,30), date);
        aula.setDataAula(dataAula);
       
        uc.addAula(aula);

        List<UnidadeCurricular> ucs = new ArrayList<>();
        ucs.add(uc);

        horario.addUnidadeCurricular(uc);


        String jsonData = FileExporter.horarioToJson(horario);
        Assertions.assertNotNull(jsonData);
        Assertions.assertTrue(jsonData.contains("LEI"));
        Assertions.assertTrue(jsonData.contains("Programação Orientada a Objetos"));
        Assertions.assertTrue(jsonData.contains("LEI01"));
        Assertions.assertTrue(jsonData.contains("Manhã"));
        Assertions.assertTrue(jsonData.contains("30"));
        Assertions.assertTrue(jsonData.contains("Seg"));
        Assertions.assertTrue(jsonData.contains("08:00:00"));
        Assertions.assertTrue(jsonData.contains("09:30:00"));
        Assertions.assertTrue(jsonData.contains("01/01/2022"));
        Assertions.assertTrue(jsonData.contains("A101"));
        Assertions.assertTrue(jsonData.contains("40"));
    }

}
