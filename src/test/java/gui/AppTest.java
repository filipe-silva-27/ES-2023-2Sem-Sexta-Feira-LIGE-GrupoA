package gui;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
class AppTest {

    private App app;

    @BeforeEach
    void setUp() {
        app = new App();
    }

    @Test
    @DisplayName("Check if main frame is not null")
    void testMainFrameNotNull() {
        assertNotNull(app.getFrame(), "Main frame should not be null");
    }

    @Test
    @DisplayName("Check if main panel is not null")
    void testMainPanelNotNull() {
        assertNotNull(app.getMainPanel(), "Main panel should not be null");
    }

    @Test
    @DisplayName("Check if main menu view is not null")
    void testMainMenuViewNotNull() {
        assertNotNull(app.getMainMenuView(), "Main menu view should not be null");
    }

    @Test
    @DisplayName("Check if upload files view is not null")
    void testUploadFilesViewNotNull() {
        assertNotNull(app.getImportFilesView(), "Upload files view should not be null");
    }

    @Test
    @DisplayName("Check if show schedule view is not null")
    void testShowScheduleViewNotNull() {
        assertNotNull(app.getShowScheduleView(), "Show schedule view should not be null");
    }

    @Test
    @DisplayName("Check if create schedule view is not null")
    void testCreateScheduleViewNotNull() {
        assertNotNull(app.getCreateScheduleView(), "Create schedule view should not be null");
    }

    @Test
    @DisplayName("Check if convert files view is not null")
    void testConvertFilesViewNotNull() {
        assertNotNull(app.getConvertFilesView(), "Convert files view should not be null");
    }

    @Test
    @DisplayName("Check if export files view is not null")
    void testExportFilesViewNotNull() {
        assertNotNull(app.getExportFilesView(), "Export files view should not be null");
    }
}
