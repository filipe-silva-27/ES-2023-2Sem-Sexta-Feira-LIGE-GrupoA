package gui;

import static org.junit.Assert.*;

import models.Schedule;
import org.junit.*;
import org.mockito.*;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.time.DayOfWeek;
import java.util.EnumMap;
import javax.swing.*;

public class AppTest {

    private App app;
    private Schedule schedule;

    @Before
    public void setUp() {
        app = Mockito.spy(new App());
        schedule = new Schedule("Test Schedule", 7);
        app.setSchedule(schedule);
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
        Schedule s = app.getSchedule();
        assertNotNull(s);
        assertEquals(s, schedule);
    }

    /**
     * Description: Test method for setSchedule.
     */
    @Test
    public void testSetSchedule() {
        Schedule s = new Schedule("Another Test Schedule", 14);
        app.setSchedule(s);
        assertEquals(s, app.getSchedule());
    }
}