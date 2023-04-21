package views;

import controllers.ViewController;
import gui.App;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

class CreateScheduleViewTest {

    @Test
    void initFrame() {
        ViewController viewController = new ViewController(new App());
        CreateScheduleView view = new CreateScheduleView(viewController);
        view.initFrame();

        assertNotNull(view.getComponent(0));
        assertTrue(view.getComponent(0) instanceof JButton);
        assertEquals("Voltar", ((JButton) view.getComponent(0)).getText());
    }
}