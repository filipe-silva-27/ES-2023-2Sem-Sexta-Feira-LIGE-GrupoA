package views;

import controllers.ExportController;
import controllers.ViewController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ExportFilesViewTest {

    @Mock
    private ViewController viewController;

    @Mock
    private ExportController exportController;

    private ExportFilesView exportFilesView;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        exportFilesView = new ExportFilesView(viewController);
        exportFilesView.setController(exportController);
    }

    @Test
    void testInitFrame_ExportLocalButtonClicked() {
        // Setup
        JButton exportLocalBtn = new JButton("Exportar para o Computador");
        exportLocalBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                exportController.exportToLocal();
            }
        });
        when(viewController.getHorario().getFileExtension()).thenReturn("csv");

        // Execution
        exportLocalBtn.doClick();

        // Verification
        verify(exportController).exportToLocal();
        verifyNoMoreInteractions(exportController);
        verify(viewController).getHorario();
        verifyNoMoreInteractions(viewController);
    }

    @Test
    void testInitFrame_ExportRemoteButtonClicked() {
        // Setup
        JButton exportRemoteBtn = new JButton("Exportar para a Nuvem GitHub Gist");
        exportRemoteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                exportController.exportToRemote();
            }
        });
        when(viewController.getHorario().getFileExtension()).thenReturn("json");

        // Execution
        exportRemoteBtn.doClick();

        // Verification
        verify(exportController).exportToRemote();
        verifyNoMoreInteractions(exportController);
        verify(viewController).getHorario();
        verifyNoMoreInteractions(viewController);
    }

    @Test
    void testInitFrame_BackButtonClicked() {
        // Setup
        JButton backBtn = new JButton("Voltar");
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                viewController.showMainMenuView();
            }
        });

        // Execution
        backBtn.doClick();

        // Verification
        verify(viewController).showMainMenuView();
        verifyNoMoreInteractions(viewController);
    }

    @Test
    void testInitFrame_Visibility() {
        // Setup
        JButton exportLocalBtn = new JButton("Exportar para o Computador");
        JButton exportRemoteBtn = new JButton("Exportar para a Nuvem GitHub Gist");

        // Mocks
        when(viewController.getHorario().getFileExtension()).thenReturn("csv");
        exportFilesView.initFrame();

        // Verification
        assertEquals(true, exportLocalBtn.isVisible());
        assertEquals(false, exportRemoteBtn.isVisible());
        verify(viewController, times(2)).getHorario();
        verifyNoMoreInteractions(viewController);

        // Mocks
        when(viewController.getHorario().getFileExtension()).thenReturn("json");
        exportFilesView.initFrame();

        // Verification
        assertEquals(false, exportLocalBtn.isVisible());
        assertEquals(true, exportRemoteBtn.isVisible());
        verify(viewController, times(4)).getHorario();
        verifyNoMoreInteractions(viewController);
    }
}
