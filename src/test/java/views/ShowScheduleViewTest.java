package views;

import controllers.ShowScheduleController;
import controllers.ViewController;
import gui.App;
import models.Aula;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.swing.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ShowScheduleViewTest {

    private ShowScheduleView view;
    private ViewController controller;
    @BeforeEach
    public void setUp() {
        controller = Mockito.mock(ShowScheduleController.class);
        view = new ShowScheduleView(controller);
    }
    @Test
    void initFrame() {
        JButton verAulas = new JButton("Ver aulas");

        // Mocking the getAulas method to return an empty list
        Mockito.when(((ShowScheduleController) controller).getAulas()).thenReturn(new ArrayList<Aula>());

        view.initFrame();
        assertEquals(1, view.getComponentCount());
        assertTrue(view.getComponent(0) instanceof JButton);
        assertEquals(verAulas.getText(), ((JButton) view.getComponent(0)).getText());
    }
}