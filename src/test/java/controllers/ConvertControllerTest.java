package controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import gui.App;
import models.Horario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import utils.exporter.FileExporter;

import javax.swing.*;

import java.io.IOException;

class ConvertControllerTest {

    private ConvertController controller;
    private App app;

    @BeforeEach
    public void setUp() {
        app = new App();
        controller = new ConvertController(app);
        
    }

}
