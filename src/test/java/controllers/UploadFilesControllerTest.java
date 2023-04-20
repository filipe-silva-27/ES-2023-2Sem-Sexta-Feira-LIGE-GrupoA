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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UploadFilesControllerTest {

    private UploadFilesController uploadFilesController;

    @Mock
    private App app;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        uploadFilesController = new UploadFilesController(app);
    }

    @Test
    @DisplayName("Test import local file with csv format")
    public void testImportLocalFileWithCsvFormat() throws IOException {
        // Setup
        JFileChooser fileChooser = mock(JFileChooser.class);
        when(fileChooser.showOpenDialog(null)).thenReturn(JFileChooser.APPROVE_OPTION);
        Path tempFile = Files.createTempFile("temp", ".csv");
        File fromFile = tempFile.toFile();
        fromFile.deleteOnExit();

        // Exercise
        uploadFilesController.importFile(fromFile);

        // Verify
        assertEquals(fromFile, uploadFilesController.getHorario().getFile());
        uploadFilesController.showMainMenuView();
        verifyNoMoreInteractions(app);
    }

    @Test
    @DisplayName("Test import local file with json format")
    public void testImportLocalFileWithJsonFormat() throws IOException {
        // Setup
        JFileChooser fileChooser = mock(JFileChooser.class);
        when(fileChooser.showOpenDialog(null)).thenReturn(JFileChooser.APPROVE_OPTION);
        Path tempFile = Files.createTempFile("temp", ".json");
        File fromFile = tempFile.toFile();
        fromFile.deleteOnExit();

        // Exercise
        uploadFilesController.importFile(fromFile);

        // Verify
        assertEquals(fromFile, uploadFilesController.getHorario().getFile());
        uploadFilesController.showMainMenuView();
        verifyNoMoreInteractions(app);
    }

    @Test
    @DisplayName("Test import local file with invalid format")
    public void testImportLocalFileWithInvalidFormat() throws IOException {
        // Setup
        JFileChooser fileChooser = mock(JFileChooser.class);
        when(fileChooser.showOpenDialog(null)).thenReturn(JFileChooser.APPROVE_OPTION);
        Path tempFile = Files.createTempFile("temp", ".txt");
        File fromFile = tempFile.toFile();
        fromFile.deleteOnExit();

        // Exercise
        uploadFilesController.importFile(fromFile);

        // Verify
        uploadFilesController.showUploadFilesView();
        verifyNoMoreInteractions(app);
    }

    @Test
    @DisplayName("Test import local file with null file")
    public void testImportLocalFileWithNullFile() {
        // Exercise
        uploadFilesController.importFile(null);

        // Verify
        uploadFilesController.showUploadFilesView();
        verifyNoMoreInteractions(app);
    }
}
