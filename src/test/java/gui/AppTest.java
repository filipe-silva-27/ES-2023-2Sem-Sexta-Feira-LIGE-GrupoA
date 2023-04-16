package gui;

import static org.junit.Assert.*;

import models.Horario;
import org.junit.*;
import org.mockito.*;

public class AppTest {

    private App app;
    private Horario horario;

    @Before
    public void setUp() {
        app = Mockito.spy(new App());
        horario = new Horario("Test Horario", 7);
    }

    @After
    public void tearDown() {
        //TODO To be implemented
    }

    /**
     * Description: Test method for shipAt.
     */
    @Test
    public void testMain() {
        // Verify that the main method runs without exceptions
        App.main(new String[] {});
    }

    /**
     * Description: Test method for shipAt.
     */
    @Test
    public void testGetFrame() {
        //TODO to implement
    }

    /**
     * Description: Test method for shipAt.
     */
    @Test
    public void testGetMainPanel() {
    }

    /**
     * Description: Test method for getSchedule.
     */
    @Test
    public void testGetSchedule() {

    }

    /**
     * Description: Test method for setSchedule.
     */
    @Test
    public void testSetSchedule() {
        Horario s = new Horario("Another Test Horario", 14);
    }
}