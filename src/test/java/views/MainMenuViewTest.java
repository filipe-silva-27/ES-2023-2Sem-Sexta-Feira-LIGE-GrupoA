package views;

import controllers.ViewController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.swing.JButton;
import static org.mockito.Mockito.*;

class MainMenuViewTest {

    @Mock
    private ViewController viewController;

    private MainMenuView mainMenuView;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mainMenuView = new MainMenuView(viewController);
    }

    @Test
    void testInitFrame() {
        mainMenuView.initFrame();
        verify(viewController).showCreateScheduleView();
        verify(viewController).showShowScheduleView();
        verify(viewController).showConvertView();
        verify(viewController).showImportFilesView();
    }

    @Test
    void testCreateButtonAction() {
        JButton createBtn = new JButton();
        mainMenuView.initFrame();
        createBtn.addActionListener(e -> viewController.showCreateScheduleView());
        createBtn.doClick();
        verify(viewController, times(1)).showCreateScheduleView();
    }

    @Test
    void testViewButtonAction() {
        JButton viewBtn = new JButton();
        mainMenuView.initFrame();
        viewBtn.addActionListener(e -> viewController.showShowScheduleView());
        viewBtn.doClick();
        verify(viewController, times(1)).showShowScheduleView();
    }

    @Test
    void testConvertButtonAction() {
        JButton convertBtn = new JButton();
        mainMenuView.initFrame();
        convertBtn.addActionListener(e -> viewController.showConvertView());
        convertBtn.doClick();
        verify(viewController, times(1)).showConvertView();
    }

    @Test
    void testBackButtonAction() {
        JButton backBtn = new JButton();
        mainMenuView.initFrame();
        backBtn.addActionListener(e -> viewController.showImportFilesView());
        backBtn.doClick();
        verify(viewController, times(1)).showImportFilesView();
    }
}
