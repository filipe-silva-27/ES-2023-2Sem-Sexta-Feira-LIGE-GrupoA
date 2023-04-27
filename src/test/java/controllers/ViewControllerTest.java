package controllers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import gui.App;
import models.Horario;

import java.io.File;

class ViewControllerTest {

    private App app;
    private ViewController viewController;

    @BeforeEach
    void setUp() {
        app = new App();
        viewController = new ViewController(app);
    }

    @Test
    void testShowMainMenuView() {
        Horario horario = new Horario("Teste");
        viewController.setHorario(horario);
        viewController.showMainMenuView();
        viewController.setHorario(null);
        viewController.showMainMenuView();
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
    @DisplayName("Test isContentSet method")
    void testIsContentSet() {
        ViewController.setContent("Hello World");
        assertTrue(viewController.isContentSet());
    }

    @Test
    @DisplayName("Test isFileUploaded method")
    void testIsFileUploaded() {
        Horario horario = new Horario("horarioTest");
        horario.setFile(new File("test.txt"));
        ViewController.setHorario(horario);
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
