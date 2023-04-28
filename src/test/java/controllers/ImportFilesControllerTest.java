package controllers;

import gui.App;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ImportFilesControllerTest {

    private ImportFilesController importFilesController;
    private App app;

    @BeforeEach
    void setUp() {
        app = new App();
        importFilesController = new ImportFilesController(app);
    }

    @Test
    void testImportFile() throws IOException {
        // Create temporary CSV file
        File csvFile = File.createTempFile("test", ".csv");
        try (FileWriter writer = new FileWriter(csvFile)) {
            writer.write("Disciplina,Sala,Dia,Hora Início,Hora Fim,Docente\n");
            writer.write("Programação 1,D102,2ª,09:00,10:00,John Doe\n");
        }

        // Create temporary JSON file
        File jsonFile = File.createTempFile("test", ".json");
        try (FileWriter writer = new FileWriter(jsonFile)) {
            writer.write("{\n");
            writer.write("  \"disciplinas\": [\n");
            writer.write("    {\n");
            writer.write("      \"nome\": \"Programação 1\",\n");
            writer.write("      \"aulas\": [\n");
            writer.write("        {\n");
            writer.write("          \"sala\": \"D102\",\n");
            writer.write("          \"dia\": \"2ª\",\n");
            writer.write("          \"horaInicio\": \"09:00\",\n");
            writer.write("          \"horaFim\": \"10:00\",\n");
            writer.write("          \"docente\": \"John Doe\"\n");
            writer.write("        }\n");
            writer.write("      ]\n");
            writer.write("    }\n");
            writer.write("  ]\n");
            writer.write("}\n");
        }

        // Import CSV file
        importFilesController.importFile(csvFile);
        assertTrue(importFilesController.isHorarioSet());

        importFilesController.importFile(null);
        importFilesController.importFile(new File("teste"));
        importFilesController.importFile(new File("teste.json"));

    }

}
