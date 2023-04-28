package utils.importer;

import models.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.InjectMocks;

import java.io.IOException;
import java.time.LocalTime;
import java.util.Date;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import utils.importer.ImportFileReader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

class ImportFileReaderTest {

    ImportFileReader importFileReader;

    @BeforeEach
    void setUp() {
        importFileReader = new ImportFileReader();
    }


    @Test
    void testCriaUC() {
        // Create a new Horario and UC
        Horario horario = new Horario("Test Horario");
        String curso = "Test Curso";
        String unidadeCurricular = "Test UC";

        UnidadeCurricular uc = importFileReader.criaUC(curso, unidadeCurricular);
        // Check that the returned UC has the correct properties
        assertEquals(curso, uc.getCurso());
        assertEquals(unidadeCurricular, uc.getNomeUC());

        horario.addUnidadeCurricular(uc);
        UnidadeCurricular uc2 = importFileReader.criaUC(curso, unidadeCurricular);
        assertEquals(curso, uc2.getCurso());
        assertEquals(unidadeCurricular, uc2.getNomeUC());

    }

    @Test
    void testJsonToHorario() {
        // create a JSON object with one record
        JSONObject json = new JSONObject();
        json.put("Curso", "Engenharia Informática");
        json.put("Unidade Curricular", "Programação");
        json.put("Turno", "T1");
        json.put("Turma", "EI-42");
        json.put("Inscritos no turno", 20);
        json.put("Dia da semana", "Segunda");
        json.put("Hora início da aula", "08:30");
        json.put("Hora fim da aula", "10:00");
        json.put("Data da aula", "01/01/2022");
        json.put("Sala atribuída à aula", "B102");
        json.put("Lotação da sala", 30);

        JSONArray jsonArray = new JSONArray();
        jsonArray.add(json);

        // create a mock file
        File jsonFile = new File("test.json");
        try {
            FileWriter fileWriter = new FileWriter(jsonFile);
            fileWriter.write(jsonArray.toJSONString());
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Horario horario = importFileReader.jsonToHorario(jsonFile);
     // assert the results
        assertEquals(1, horario.getUnidadesCurriculares().size());
        assertEquals("Programação", horario.getUnidadeCurricularByNome("Programação").getNomeUC());
        assertEquals("Engenharia Informática", horario.getUnidadeCurricularByNome("Programação").getCurso());
        assertEquals("T1", horario.getUnidadeCurricularByNome("Programação").getAulas().get(0).getTurno());
        assertEquals("EI-42", horario.getUnidadeCurricularByNome("Programação").getAulas().get(0).getTurma());


    }







}

