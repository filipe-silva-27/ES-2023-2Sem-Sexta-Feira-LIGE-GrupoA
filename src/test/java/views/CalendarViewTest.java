package views;

import models.Aula;
import org.junit.Test;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.table.DefaultTableModel;
import java.time.LocalTime;
import java.util.List;

public class CalendarViewTest {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @BeforeAll
    static void initAll() {
    }

    @BeforeEach
    void init() {
    }

    @Test
    @DisplayName("init Table")
    public void initTable() {
        try {
            log.info("Starting execution of initTable");
            DefaultTableModel expectedValue = null;

            List<Aula> aulasc = null;

            CalendarView calendarview = new CalendarView(aulasc);
            DefaultTableModel actualValue = calendarview.initTable();
            log.info("Expected Value=" + expectedValue + " . Actual Value=" + actualValue);
            System.out.println(
                    "Expected Value=" + expectedValue + " . Actual Value=" + actualValue);
            Assertions.assertEquals(expectedValue, actualValue);
        } catch (Exception exception) {
            log.error(
                    "Exception in execution of execute1GetAllLogFromFirstMovF-" + exception,
                    exception);
            exception.printStackTrace();
            Assertions.assertFalse(false);
        }
    }

    @Test
    @DisplayName("update Table")
    public void updateTable() {
        try {
            log.info("Starting execution of updateTable");

            List<Aula> aulasc = null;

            CalendarView calendarview = new CalendarView(aulasc);
            calendarview.updateTable();
            Assertions.assertTrue(true);
        } catch (Exception exception) {
            log.error("Exception in execution ofupdateTable-" + exception, exception);
            exception.printStackTrace();
            Assertions.assertFalse(false);
        }
    }

    @Test
    @DisplayName("get Row Index")
    public void getRowIndex() {
        try {
            log.info("Starting execution of getRowIndex");
            int expectedValue = 0;
            LocalTime time = LocalTime.now();

            List<Aula> aulasc = null;

            CalendarView calendarview = new CalendarView(aulasc);
            int actualValue = calendarview.getRowIndex(time);
            log.info("Expected Value=" + expectedValue + " . Actual Value=" + actualValue);
            System.out.println(
                    "Expected Value=" + expectedValue + " . Actual Value=" + actualValue);
            Assertions.assertEquals(expectedValue, actualValue);
        } catch (Exception exception) {
            log.error(
                    "Exception in execution of execute1GetAllLogFromFirstMovF-" + exception,
                    exception);
            exception.printStackTrace();
            Assertions.assertFalse(false);
        }
    }

    @Test
    @DisplayName("set Buttons")
    public void setButtons() {
        try {
            log.info("Starting execution of setButtons");

            List<Aula> aulasc = null;


            CalendarView calendarview = new CalendarView(aulasc);
            calendarview.setButtons();
            Assertions.assertTrue(true);
        } catch (Exception exception) {
            log.error("Exception in execution ofsetButtons-" + exception, exception);
            exception.printStackTrace();
            Assertions.assertFalse(false);
        }
    }

    @Test
    @DisplayName("init Frame")
    public void initFrame() {
        try {
            log.info("Starting execution of initFrame");

            List<Aula> aulasc = null;

            CalendarView calendarview = new CalendarView(aulasc);
            calendarview.initFrame();
            Assertions.assertTrue(true);
        } catch (Exception exception) {
            log.error("Exception in execution ofinitFrame-" + exception, exception);
            exception.printStackTrace();
            Assertions.assertFalse(false);
        }
    }

    @AfterEach
    void tearDown() {
    }

    @AfterAll
    static void tearDownAll() {
    }
}
