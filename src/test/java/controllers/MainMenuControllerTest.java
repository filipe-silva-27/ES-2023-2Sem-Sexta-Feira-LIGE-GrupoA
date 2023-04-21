package controllers;

import static org.junit.jupiter.api.Assertions.*;

import models.Horario;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import gui.App;

class MainMenuControllerTest {

    @Mock
    App mockApp;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testMainMenuControllerInitialization() {
        MainMenuController mainMenuController = new MainMenuController(mockApp);
        assertNotNull(mainMenuController);
    }

    @Test
    void testShowMainMenuViewWithoutHorarioSet() {
        MainMenuController mainMenuController = new MainMenuController(mockApp);
        mainMenuController.showMainMenuView();
        assertEquals(App.UPLOAD_MENU, mainMenuController.getCardLayout().toString());
    }

    @Test
    void testShowMainMenuViewWithHorarioSet() {
        MainMenuController mainMenuController = new MainMenuController(mockApp);
        ViewController.setHorario(new Horario("horarioTeste"));
        mainMenuController.showMainMenuView();
        assertEquals(App.MAIN_MENU, mainMenuController.getCardLayout().toString());
    }

    @Test
    void testShowShowScheduleViewWithoutHorarioSet() {
        MainMenuController mainMenuController = new MainMenuController(mockApp);
        mainMenuController.showShowScheduleView();
        assertEquals(App.UPLOAD_MENU, mainMenuController.getCardLayout().toString());
    }

    @Test
    void testShowShowScheduleViewWithHorarioSet() {
        MainMenuController mainMenuController = new MainMenuController(mockApp);
        ViewController.setHorario(new Horario("horarioTeste"));
        mainMenuController.showShowScheduleView();
        assertEquals(App.SHOW_SCHEDULE_MENU, mainMenuController.getCardLayout().toString());
    }

}
