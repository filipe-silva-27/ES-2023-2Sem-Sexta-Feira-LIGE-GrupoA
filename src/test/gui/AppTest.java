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
    void setUp() {
        app = Mockito.spy(new App());
        schedule = new Schedule("Test Schedule", 7);
        app.setSchedule(schedule);
    }

    @After
    void tearDown() {
        //TODO To be implemented
    }

    /**
     * Description: Test method for shipAt.
     */
    @Test
    void testMain() {
        // Verify that the main method runs without exceptions
        App.main(new String[] {});
    }

    /**
     * Description: Test method for shipAt.
     */
    @Test
    void testGetFrame() {
        //TODO to implement
        fail("Not implemented yet");
    }

    /**
     * Description: Test method for shipAt.
     */
    @Test
    void testGetMainPanel() {
        //TODO to implement
        fail("Not implemented yet");
    }

    /**
     * Description: Test method for getSchedule.
     */
    @Test
    void testGetSchedule() {
        Schedule s = app.getSchedule();
        assertNotNull(s);
        assertEquals(s, schedule);
    }

    /**
     * Description: Test method for setSchedule.
     */
    @Test
    void testSetSchedule() {
        Schedule s = new Schedule("Another Test Schedule", 14);
        app.setSchedule(s);
        assertEquals(s, app.getSchedule());
    }
}
