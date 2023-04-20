package views;

import controllers.ViewController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.swing.JButton;
import static org.mockito.Mockito.*;

public class MainMenuViewTest {

    @Mock
    private ViewController viewController;

    private MainMenuView mainMenuView;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mainMenuView = new MainMenuView(viewController);
    }

    @Test
    public void testInitFrame() {
        mainMenuView.initFrame();
        verify(viewController).showCreateScheduleView();
        verify(viewController).showShowScheduleView();
        verify(viewController).showConvertView();
        verify(viewController).showUploadFilesView();
    }

    @Test
    public void testCreateButtonAction() {
        JButton createBtn = new JButton();
        mainMenuView.initFrame();
        createBtn.addActionListener(e -> viewController.showCreateScheduleView());
        createBtn.doClick();
        verify(viewController, times(1)).showCreateScheduleView();
    }

    @Test
    public void testViewButtonAction() {
        JButton viewBtn = new JButton();
        mainMenuView.initFrame();
        viewBtn.addActionListener(e -> viewController.showShowScheduleView());
        viewBtn.doClick();
        verify(viewController, times(1)).showShowScheduleView();
    }

    @Test
    public void testConvertButtonAction() {
        JButton convertBtn = new JButton();
        mainMenuView.initFrame();
        convertBtn.addActionListener(e -> viewController.showConvertView());
        convertBtn.doClick();
        verify(viewController, times(1)).showConvertView();
    }

    @Test
    public void testBackButtonAction() {
        JButton backBtn = new JButton();
        mainMenuView.initFrame();
        backBtn.addActionListener(e -> viewController.showUploadFilesView());
        backBtn.doClick();
        verify(viewController, times(1)).showUploadFilesView();
    }
}
