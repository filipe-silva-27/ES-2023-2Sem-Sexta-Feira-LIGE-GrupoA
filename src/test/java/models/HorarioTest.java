package models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;

class HorarioTest {
    private Horario horario;

    @BeforeEach
    void setup() {
        horario = new Horario("Horario 1");
        UnidadeCurricular uc1 = new UnidadeCurricular("LEI", "Inteligência Artificial");
        UnidadeCurricular uc2 = new UnidadeCurricular("LIGE", "Engenharia de Software");
        horario.addUnidadeCurricular(uc1);
        horario.addUnidadeCurricular(uc2);
    }
    @Test
    void addUnidadeCurricular() {
        UnidadeCurricular uc3 = new UnidadeCurricular("LIGE", "Inteligência Artificial");
        boolean result = horario.addUnidadeCurricular(uc3);
        Assertions.assertTrue(result);
        Assertions.assertEquals(3, horario.getUnidadesCurriculares().size());
    }

    @Test
    void getUnidadeCurricularByNome() {
        UnidadeCurricular uc = horario.getUnidadeCurricularByNome("UC1");
        Assertions.assertNotNull(uc);
        Assertions.assertEquals("UC1", uc.getNomeUC());
    }

    @Test
    void getUnidadeCurricular() {
        UnidadeCurricular uc1 = new UnidadeCurricular("LIGE", "Inteligência Artificial");
        UnidadeCurricular uc = horario.getUnidadeCurricular(uc1);
        Assertions.assertNotNull(uc);
        Assertions.assertEquals("UC1", uc.getNomeUC());
    }

    @Test
    void getName() {
        Horario h = new Horario("H1");
        Horario h2 = horario;
        Assertions.assertNotNull(h2);
        Assertions.assertEquals(h.getName(), h2.getName());
    }

    @Test
    void setName() {
        horario.setName("Horario 2");
        Assertions.assertEquals("Horario 2", horario.getName());
    }

    @Test
    void getFile() {
        File expectedFile = new File("horario.xml");
        Horario horario = new Horario("Horario 1");
        horario.setFile(expectedFile);
        File actualFile = horario.getFile();
        Assertions.assertEquals(expectedFile, actualFile);
    }

    @Test
    void setFile() {
        File file = new File("horario.csv");
        horario.setFile(file);
        Assertions.assertEquals(file, horario.getFile());
    }

    @Test
    void getFileExtension() {
        File file = new File("horario.csv");
        horario.setFile(file);
        Assertions.assertEquals("csv", horario.getFileExtension());
    }

    @Test
    void testToString() {
        Horario horario = new Horario("Horário 1");

        UnidadeCurricular uc1 = new UnidadeCurricular("LEI", "Inteligência Artificial");
        UnidadeCurricular uc2 = new UnidadeCurricular("LIGE", "Engenharia de Software");
        UnidadeCurricular uc3 = new UnidadeCurricular("LIGE", "Inteligência Artificial");

        horario.addUnidadeCurricular(uc1);
        horario.addUnidadeCurricular(uc2);
        horario.addUnidadeCurricular(uc3);

        String expected = "LEI (Inteligência Artificial)\nLIGE (Engenharia de Software)\nLIGE (Inteligência Artificial)\n";

        Assertions.assertEquals(expected, horario.toString());
    }
}