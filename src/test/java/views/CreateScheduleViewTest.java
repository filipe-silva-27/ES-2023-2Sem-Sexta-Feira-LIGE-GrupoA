package views;

import controllers.CreateScheduleController;
import controllers.ViewController;
import gui.App;
import models.Aula;
import models.Horario;
import models.UnidadeCurricular;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import java.awt.Component;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import static views.CreateScheduleView.AulaCheckBoxListRenderer;
import static views.CreateScheduleView.UCCheckBoxListRenderer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateScheduleViewTest {
	
	CreateScheduleController controller;
    ViewController viewController;
    
	
	 @BeforeEach
    public void setUp() {
		App app = new App();
		controller = new CreateScheduleController(app);
		viewController = mock(ViewController.class);
    }

    @AfterEach
    public void tearDown() {
        controller = null;
        viewController = null;
    }


    @Test
    void initFrame() {
        ViewController viewController = new ViewController(new App());
        CreateScheduleView view = new CreateScheduleView(viewController);
        view.initFrame();

        assertNotNull(view.getComponent(0));
        assertTrue(view.getComponent(0) instanceof JButton);
        assertEquals("Voltar", ((JButton) view.getComponent(0)).getText());
    }
    
    @Test
    void testUCCheckBoxListRenderer() {
        UCCheckBoxListRenderer renderer = new UCCheckBoxListRenderer();
        JList<UnidadeCurricular> list = mock(JList.class);
        UnidadeCurricular uc = new UnidadeCurricular("LEI", "Programação Orientada a Objetos");
        Component component = renderer.getListCellRendererComponent(list, uc, 0, false, false);
        assertTrue(component instanceof JCheckBox);
        assertEquals(uc.getNomeUC(), ((JCheckBox) component).getText());
        assertFalse(((JCheckBox) component).isSelected());
        assertTrue(((JCheckBox) component).isEnabled());
    }

}