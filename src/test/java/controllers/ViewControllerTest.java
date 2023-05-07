package controllers;

import static org.junit.jupiter.api.Assertions.*;
import java.awt.CardLayout;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import gui.App;
import models.Horario;

import java.io.File;

class ViewControllerTest {

    private App app;
    private ViewController viewController;

    @Test
    void testShowMainMenuView() {
    	Horario horario = new Horario("Teste");
    	viewController.setHorario(horario);
    	viewController.showMainMenuView();
    	viewController.setHorario(null);
    	viewController.showMainMenuView();
    }
    
    @Test
    void testShowShowScheduleView() {
    	Horario horario = new Horario("Teste");
        viewController.setHorario(horario);
    	viewController.showShowScheduleView();
    	viewController.setHorario(null);
    	viewController.showShowScheduleView();
    }
    
    @BeforeEach
    void setUp() {
        app = new App();
        viewController = new ViewController(app);
    }

    @Test
    @DisplayName("Test getContent method")
    void testGetContent() {
        String expected = "Hello World";
        ViewController.setContent(expected);
        String actual = ViewController.getContent();
        assertEquals(expected, actual);
    }

    @Test
    void testIsContentSet() {
        ViewController viewController = new ViewController(app);
        assertFalse(viewController.isContentSet());

        viewController.setContent("test");
        assertTrue(viewController.isContentSet());

        viewController.setContent(null);
        assertFalse(viewController.isContentSet());
    }


    @Test
    void testIsFileUploaded() {
        // Create a Horario object with a null file
        Horario horario1 = new Horario("Test Horario 1");
        horario1.setFile(null);
        viewController.setHorario(horario1);
        assertFalse(viewController.isFileUploaded());

        // Create a Horario object with a non-null file
        Horario horario2 = new Horario("Test Horario 2");
        horario2.setFile(new File("dummy/path"));
        viewController.setHorario(horario2);
        assertTrue(viewController.isFileUploaded());
    }
   

    @Test
    @DisplayName("Test isHorarioSet method")
    void testIsHorarioSet() {
        Horario horario = new Horario("horarioTest");
        ViewController.setHorario(horario);
        assertTrue(viewController.isHorarioSet());
    }

    @Test
    @DisplayName("Test getApp method")
    void testGetApp() {
        assertEquals(app, viewController.getApp());
    }

    @Test
    @DisplayName("Test getHorario method")
    void testGetHorario() {
        Horario horario = new Horario("horarioTest");
        ViewController.setHorario(horario);
        assertEquals(horario, viewController.getHorario());
    }

    @Test
    @DisplayName("Test setHorario method")
    void testSetHorario() {
        Horario expected = new Horario("horarioTest");
        ViewController.setHorario(expected);
        Horario actual = viewController.getHorario();
        assertEquals(expected, actual);
    }

    // add more tests for the other methods as needed


}
