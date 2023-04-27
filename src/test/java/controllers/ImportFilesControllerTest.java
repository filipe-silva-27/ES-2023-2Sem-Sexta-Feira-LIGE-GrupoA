package controllers;

import gui.App;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ImportFilesControllerTest {

    private ImportFilesController importFilesController;

    @Mock
    private App app;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        importFilesController = new ImportFilesController(app);
    }

    @Test
    @DisplayName("Test import local file with csv format")
    void testImportLocalFileWithCsvFormat() throws IOException {
        // Setup
        JFileChooser fileChooser = mock(JFileChooser.class);
        when(fileChooser.showOpenDialog(null)).thenReturn(JFileChooser.APPROVE_OPTION);
        Path tempFile = Files.createTempFile("temp", ".csv");
        File fromFile = tempFile.toFile();
        fromFile.deleteOnExit();

        // Exercise
        importFilesController.importFile(fromFile);

        // Verify
        assertEquals(fromFile, importFilesController.getHorario().getFile());
        importFilesController.showMainMenuView();
        verifyNoMoreInteractions(app);
    }

    @Test
    @DisplayName("Test import local file with json format")
    void testImportLocalFileWithJsonFormat() throws IOException {
        // Setup
        JFileChooser fileChooser = mock(JFileChooser.class);
        when(fileChooser.showOpenDialog(null)).thenReturn(JFileChooser.APPROVE_OPTION);
        Path tempFile = Files.createTempFile("temp", ".json");
        File fromFile = tempFile.toFile();
        fromFile.deleteOnExit();

        // Exercise
        importFilesController.importFile(fromFile);

        // Verify
        assertEquals(fromFile, importFilesController.getHorario().getFile());
        importFilesController.showMainMenuView();
        verifyNoMoreInteractions(app);
    }

    @Test
    @DisplayName("Test import local file with invalid format")
    void testImportLocalFileWithInvalidFormat() throws IOException {
        // Setup
        JFileChooser fileChooser = mock(JFileChooser.class);
        when(fileChooser.showOpenDialog(null)).thenReturn(JFileChooser.APPROVE_OPTION);
        Path tempFile = Files.createTempFile("temp", ".txt");
        File fromFile = tempFile.toFile();
        fromFile.deleteOnExit();

        // Exercise
        importFilesController.importFile(fromFile);

        // Verify
        importFilesController.showImportFilesView();
        verifyNoMoreInteractions(app);
    }

    @Test
    @DisplayName("Test import local file with null file")
    void testImportLocalFileWithNullFile() {
        // Exercise
        importFilesController.importFile(null);

        // Verify
        importFilesController.showImportFilesView();
        verifyNoMoreInteractions(app);
    }
}
