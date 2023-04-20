package views;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import controllers.ConvertController;
import controllers.ViewController;

import javax.swing.*;
import java.awt.*;

class ConvertFilesViewTest {

    ConvertFilesView view;
    ViewController viewController;

    @BeforeEach
    void setUp() {
        viewController = Mockito.mock(ViewController.class);
        view = new ConvertFilesView(viewController);
    }

    @Test
    void initFrame_withCSVFile_shouldShowConvertToJSONButton() {
        Mockito.when(viewController.getHorario().getFileExtension()).thenReturn("csv");

        view.initFrame();

        Component[] components = view.getComponents();
        assertEquals("Converter CSV para JSON", ((JButton)components[0]).getText());
        assertFalse(components[0].isVisible());
        assertTrue(components[1].isVisible());
    }

    @Test
    void initFrame_withJSONFile_shouldShowConvertToCSVButton() {
        Mockito.when(viewController.getHorario().getFileExtension()).thenReturn("json");

        view.initFrame();

        Component[] components = view.getComponents();
        assertEquals("Converter JSON para CSV", ((JButton)components[1]).getText());
        assertFalse(components[1].isVisible());
        assertTrue(components[0].isVisible());
    }

    @Test
    void initFrame_backButton_shouldShowAndCallMainMenuView() {
        view.initFrame();

        Component[] components = view.getComponents();
        JButton backButton = (JButton) components[2];
        assertTrue(backButton.isVisible());

        backButton.doClick();

        Mockito.verify(viewController, Mockito.times(1)).showMainMenuView();
    }

    @Test
    void initFrame_convertButton_shouldCallConvertFile() {
        view.initFrame();

        Component[] components = view.getComponents();
        JButton convertButton = (JButton) components[0];
        assertTrue(convertButton.isVisible());

        convertButton.doClick();

        Mockito.verify((ConvertController)viewController, Mockito.times(1)).convertFile();
    }
}
