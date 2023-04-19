package views;

import java.awt.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import controllers.ShowScheduleController;
import models.Aula;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CalendarView extends JFrame {
    private List<Aula> aulas;
    private LocalDate startDate;
    private JLabel weekLabel = new JLabel();
    private JTable calendarTable;
    // Set row headers for the times of the day
    private String[] timeIntervals = {"", "8:00-9:30", "9:30-11:00", "11:00-12:30", "12:30-13:00", "13:00-14:30",
            "14:30-16:00", "16:00-17:30", "17:30-19:00"};
    private static final Logger logger = LoggerFactory.getLogger(CalendarView.class);


    public CalendarView(List<Aula> aulas) {
        this.aulas = aulas;
        this.startDate = this.aulas.get(0).
                getDataAula().getData().
                toInstant().atZone(ZoneId.systemDefault()).toLocalDate().with(DayOfWeek.MONDAY);
        init();
    }

    private void updateTable() {
        // Clear the table
        DefaultTableModel model = (DefaultTableModel) calendarTable.getModel();
        model.setRowCount(0);
        model.setColumnCount(0);

        // Set column headers for the days of the week with dates
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd");
        String[] daysOfWeek = {"", startDate.format(formatter), startDate.plusDays(1).format(formatter),
                startDate.plusDays(2).format(formatter), startDate.plusDays(3).format(formatter),
                startDate.plusDays(4).format(formatter), startDate.plusDays(5).format(formatter),
                startDate.plusDays(6).format(formatter)};
        for (int i = 0; i < 7; i++) {
            model.addColumn(daysOfWeek[i]);
        }


        for (int i = 1; i < 9; i++) {
            model.addRow(new Object[]{timeIntervals[i]});
        }

        // Add the classes as events to the calendar for the current week
        LocalDate endDate = startDate.plusDays(7);
        weekLabel.setText("Week of " + startDate + " - " + endDate.minusDays(1).format(formatter));
        for (Aula aula : aulas) {
            Date date = aula.getDataAula().getData();
            LocalDate aulaDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            if (aulaDate.isAfter(startDate) && aulaDate.isBefore(endDate)) {
                LocalTime startHour = aula.getDataAula().getHoraInicio();
                LocalTime endHour = aula.getDataAula().getHoraFim();
                String time = String.valueOf(startHour+"-"+endHour);
                String eventName = aula.getUc().getNomeUC();
                int startRow = getRowIndex(time);
                if (startRow == -1) continue;
                logger.debug(aula.toString());

                Object currentValue = model.getValueAt(startRow - 1, aula.getDataAula().getDiaSemana().ordinal() + 1);
                if (currentValue == null) {
                    model.setValueAt("<html>" + eventName + "</html>",
                            startRow - 1, aula.getDataAula().getDiaSemana().ordinal() + 1);
                } else {
                    model.setValueAt("<html>" + currentValue + "<br>" + eventName + "</html>", startRow - 1,
                            aula.getDataAula().getDiaSemana().ordinal() + 1);
                }


            }
        }
    }

    private int getRowIndex(String time) {
        for(int i=0; i<timeIntervals.length; i++){
            if (timeIntervals[i].equals(time)) {
                return i;
            }
        }
        return -1;
    }

    private void init() {

        // Create a table to display the calendar
        DefaultTableModel model = new DefaultTableModel();
        calendarTable = new JTable(model);
        calendarTable.setRowHeight(60);

        updateTable();

        // Add the table to a JPanel
        JPanel panel = new JPanel(new BorderLayout());

        // Add a label and buttons for the current week
        JPanel weekPanel = new JPanel(new BorderLayout());
        weekPanel.add(weekLabel, BorderLayout.WEST);
        panel.add(weekPanel, BorderLayout.NORTH);

        JButton prevButton = new JButton("Previous");
        prevButton.addActionListener(e -> {
            startDate = startDate.minusDays(7);
            updateTable();
        });
        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(e -> {
            startDate = startDate.plusDays(7);
            updateTable();
        });
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(prevButton);
        buttonPanel.add(nextButton);
        panel.add(buttonPanel, BorderLayout.EAST);

        // Customize the look and feel of the table
        TableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        ((DefaultTableCellRenderer) centerRenderer).setHorizontalAlignment(SwingConstants.CENTER);
        calendarTable.setDefaultRenderer(Object.class, centerRenderer);
        calendarTable.getTableHeader().setBackground(Color.white);
        calendarTable.getTableHeader().setReorderingAllowed(false);
        calendarTable.setGridColor(Color.black);
        calendarTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        calendarTable.getColumnModel().getColumn(0).setPreferredWidth(50);

        panel.add(calendarTable.getTableHeader(), BorderLayout.PAGE_START);
        panel.add(calendarTable, BorderLayout.CENTER);

        // Add the JPanel to a JFrame
        this.getContentPane().add(new JScrollPane(panel));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setVisible(true);
    }


}
