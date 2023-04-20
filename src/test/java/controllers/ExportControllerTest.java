package controllers;

import gui.App;
import models.Horario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import utils.uploader.FileUploader;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ExportControllerTest {

    private ExportController controller;

    @Mock
    private App app;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new ExportController(app);
    }

    @Test
    public void testExportToLocal() {
        controller.setContent("Test content");
        controller.setHorario(new Horario("horarioTeste"));

        JFileChooser fileChooser = mock(JFileChooser.class);
        when(fileChooser.showSaveDialog(null)).thenReturn(JFileChooser.APPROVE_OPTION);
        File file = new File("test.txt");
        when(fileChooser.getSelectedFile()).thenReturn(file);

        assertDoesNotThrow(() -> controller.exportToLocal());

        assertTrue(file.exists());
        assertTrue(file.delete());
    }

    @Test
    public void testExportToRemote() throws IOException {
        controller.setContent("Test content");

        FileUploader fileUploader = mock(FileUploader.class);
        when(fileUploader.exportToGist(anyString(), anyString())).thenReturn("https://gist.github.com/abc123");

        JOptionPane pane = mock(JOptionPane.class);
        when(pane.showInputDialog(null, "Introduza o nome do ficheiro (com a extensão):")).thenReturn("test.txt");

        assertDoesNotThrow(() -> controller.exportToRemote());

        verify(pane, times(1)).showInputDialog(null, "Introduza o nome do ficheiro (com a extensão):");
        verify(fileUploader, times(1)).exportToGist(eq("test.txt"), eq("Test content"));
    }

    @Test
    public void testExportToRemoteEmptyContent() throws IOException {
        assertThrows(IllegalArgumentException.class, () -> controller.exportToRemote());
    }
}