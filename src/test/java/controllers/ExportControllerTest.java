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

class ExportControllerTest {

    private ExportController controller;
    private App app;

    @BeforeEach
    public void setUp() {
    	app = new App();
        controller = new ExportController(app);
    }

    @Test
    void testExportToLocal() {
    	ViewController viewController = new ViewController(app);
        // Set up
        viewController.setContent(null);

        // Call the method
        controller.exportToLocal();
        
        viewController.setContent("okkk");
        controller.exportToLocal();
        
        viewController.setHorario(new Horario("okk"));
        controller.exportToLocal();

    }

    

    @Test
    void testExportToRemote() throws IOException {
        controller.setContent("Test content");

        FileUploader fileUploader = mock(FileUploader.class);
        when(fileUploader.exportToGist(anyString(), anyString())).thenReturn("https://gist.github.com/abc123");

        JOptionPane pane = mock(JOptionPane.class);
        when(pane.showInputDialog(null, "Introduza o nome do ficheiro (com a extensão):")).thenReturn("test.txt");

        assertDoesNotThrow(() -> controller.exportToRemote());

        verify(pane, times(1)).showInputDialog(null, "Introduza o nome do ficheiro (com a extensão):");
        verify(fileUploader, times(1)).exportToGist("test.txt", "Test content");
    }

    @Test
    void testExportToRemoteEmptyContent() throws IOException {
        assertThrows(IllegalArgumentException.class, () -> controller.exportToRemote());
    }
}
