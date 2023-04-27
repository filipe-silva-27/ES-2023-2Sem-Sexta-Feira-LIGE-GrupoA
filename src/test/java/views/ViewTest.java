package views;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.*;

import controllers.ViewController;
import models.Horario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.JButton;

class ViewTest {
    private ViewController mockViewController;
    private View viewUnderTest;

    @BeforeEach
    void setUp() {
        mockViewController = mock(ViewController.class);
        viewUnderTest = new View(mockViewController) {
            @Override
            public void initFrame() {
                // no implementation needed for this test
            }
        };
    }

    @Test
    void testSetController() {
        // GIVEN
        ViewController newController = mock(ViewController.class);

        // WHEN
        viewUnderTest.setController(newController);

        // THEN
        assertSame(newController, viewUnderTest.viewController);
    }

    @Test
    void testInitFrameAddsButtons() {
        // GIVEN
        JButton mockButton1 = mock(JButton.class);
        JButton mockButton2 = mock(JButton.class);

        when(mockViewController.getHorario()).thenReturn(new Horario("horarioTest"));

        doAnswer(invocation -> {
            viewUnderTest.add(mockButton1);
            viewUnderTest.add(mockButton2);
            return null;
        }).when(mockViewController).showImportFilesView();

        // WHEN
        viewUnderTest.initFrame();

        // THEN
        verify(mockButton1).addActionListener(any());
        verify(mockButton2).addActionListener(any());
        verify(mockButton1).getText();
        verify(mockButton2).getText();
        verify(mockViewController).getHorario();
        verify(mockViewController).showImportFilesView();
    }
}
