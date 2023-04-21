package controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import gui.App;
import models.Horario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

import utils.uploader.FileUploader;

import javax.swing.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class ConvertControllerTest {

    private ConvertController controller;
    private App app;

    @BeforeEach
    public void setUp() {
        app = mock(App.class);
        controller = new ConvertController(app);
    }

    @Test
    void testConvertFile() throws IOException {
        Horario horario = mock(Horario.class);
        when(controller.getHorario()).thenReturn(horario);
        when(FileUploader.convertHorarioToFormat(horario)).thenReturn("{\"name\":\"John\", \"age\":30, \"city\":\"New York\"}");

        assertDoesNotThrow(() -> controller.convertFile());

        ViewController.setContent("{\"name\":\"John\", \"age\":30, \"city\":\"New York\"}");
        controller.showExportFilesView();
    }

    @Test
    void testConvertFileWithInvalidFormat() throws IOException {
        Horario horario = mock(Horario.class);
        when(controller.getHorario()).thenReturn(horario);
        when(FileUploader.convertHorarioToFormat(horario)).thenThrow(new IllegalArgumentException());

        JOptionPane jOptionPane = mock(JOptionPane.class);
        when(app.getFrame().getContentPane()).thenReturn(jOptionPane);

        controller.convertFile();

        verify(jOptionPane).showMessageDialog(null, "O ficheiro não tem um formato válido",
                "Error", JOptionPane.ERROR_MESSAGE);
    }

    @Test
    void testConvertFileWithoutHorarioSet() throws IOException {
        when(controller.getHorario()).thenReturn(null);

        JOptionPane jOptionPane = mock(JOptionPane.class);
        when(app.getFrame().getContentPane()).thenReturn(jOptionPane);

        controller.convertFile();

        verify(jOptionPane).showMessageDialog(null, "Por favor faça upload de um ficheiro primeiro!",
                "Error", JOptionPane.ERROR_MESSAGE);
    }
}
