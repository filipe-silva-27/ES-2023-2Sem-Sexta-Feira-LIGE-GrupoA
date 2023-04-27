package controllers;

import gui.App;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import utils.ImportFileReader;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.FileWriter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UploadFilesControllerTest {

    private UploadFilesController uploadFilesController;
    private App app;

    @BeforeEach
    void setUp() {
        app = new App();
        uploadFilesController = new UploadFilesController(app);
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
        uploadFilesController.importFile(csvFile);
        assertTrue(uploadFilesController.isHorarioSet());

        uploadFilesController.importFile(null);
        uploadFilesController.importFile(new File("teste"));
        uploadFilesController.importFile(new File("teste.json"));

    }

}
