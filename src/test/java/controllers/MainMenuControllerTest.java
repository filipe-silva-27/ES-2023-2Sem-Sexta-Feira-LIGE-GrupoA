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

    @Test
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


}
