package views;

import controllers.ImportFilesController;
import controllers.ViewController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import javax.swing.JButton;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ImportFilesViewTest {

    private ImportFilesView view;


    @Mock
    private ViewController viewController;

    @BeforeEach
    void setUp() {
        viewController = mock(ViewController.class);
        view = new ImportFilesView(viewController);
    }

    @Test
    void testInitFrame() {
        view.initFrame();
        assertEquals(2, view.getComponentCount());
        assertEquals(JButton.class, view.getComponent(0).getClass());
        assertEquals(JButton.class, view.getComponent(1).getClass());
    }

    @Test
    void testLocalBtnAction() {
        ImportFilesController controller = mock(ImportFilesController.class);
        ImportFilesView view = new ImportFilesView(controller);
        view.initFrame();
        JButton localBtn = (JButton) view.getComponent(0);
        localBtn.doClick();
        verify(controller).importLocalFile();
    }

    @Test
    void testRemoteBtnAction() {
        ImportFilesController controller = mock(ImportFilesController.class);
        ImportFilesView view = new ImportFilesView(controller);
        view.initFrame();
        JButton remoteBtn = (JButton) view.getComponent(1);
        remoteBtn.doClick();
        verify(controller).importRemoteFile();
    }
}
