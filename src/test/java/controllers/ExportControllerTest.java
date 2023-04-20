package controllers;

import gui.App;
import models.Horario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ExportControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(ExportControllerTest.class);
    private App app;
    private ExportController controller;

    @BeforeEach
    void setUp() {
        app = new App();
        controller = new ExportController(app);
    }

    @Test
    @DisplayName("Test export to local file")
    void testExportToLocal() {
        controller.setContent("Test content");
        controller.setHorario(new Horario("horarioTeste"));

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setSelectedFile(new File("test.json"));

        assertDoesNotThrow(() -> {
            SwingUtilities.invokeAndWait(() -> {
                int result = fileChooser.showSaveDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File fileTo = fileChooser.getSelectedFile();
                    try {
                        controller.exportToLocal();
                        assertTrue(fileTo.exists());
                    } catch (Exception e) {
                        logger.error("Error exporting to local file: {}", e.getMessage());
                        fail("Error exporting to local file: " + e.getMessage());
                    }
                }
            });
        });
    }

    @Test
    @DisplayName("Test export to remote file")
    void testExportToRemote() {
        controller.setContent("Test content");
        controller.setHorario(new Horario("horarioTeste"));

        String url = assertDoesNotThrow(() ->  controller.exportToRemote());
        assertNotNull(url);
        assertTrue(url.startsWith("https://gist.github.com/"));
    }

    @Test
    @DisplayName("Test export with empty content")
    void testExportWithEmptyContent() {
        controller.setContent(null);
        controller.setHorario(new File("test.csv"));

        assertThrows(IllegalArgumentException.class, () -> controller.exportToLocal(new File("test.json")));
        assertThrows(IOException.class, () -> controller.exportToRemote("test.json"));
    }

    @Test
    @DisplayName("Test export with empty file name")
    void testExportWithEmptyFileName() {
        controller.setContent("Test content");
        controller.setHorario(new File("test.csv"));

        assertThrows(IllegalArgumentException.class, () -> controller.exportToRemote(""));
    }
}
