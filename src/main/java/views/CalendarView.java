package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import models.Aula;

public class CalendarView extends JFrame {
    private List<Aula> aulas;

    public CalendarView(List<Aula> aulas) {
        this.aulas = aulas;
        init();
    }

    private void init() {
        // Create a table to display the calendar
        DefaultTableModel model = new DefaultTableModel(25, 8) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable calendarTable = new JTable(model);
        calendarTable.setRowHeight(60);

        // Set column headers for the days of the week
        String[] daysOfWeek = {"", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        for (int i = 0; i < 8; i++) {
            model.setValueAt(daysOfWeek[i], 0, i);
        }

// Set row headers for the times of the day
        String[] timeIntervals = {"", "8:00-9:30", "9:30-11:00", "11:00-12:30", "12:30-13:00", "13:00-14:30", "14:30-16:00", "16:00-17:30", "17:30-19:00"};
        for (int i = 1; i < 9; i++) {
            model.setValueAt(timeIntervals[i], i, 0);
        }

// Add the classes as events to the calendar
        for (Aula aula : aulas) {
            LocalTime startHour = aula.getDataAula().getHoraInicio();
            LocalTime endHour = aula.getDataAula().getHoraFim();
            String eventName = aula.getUc().getNomeUC();

            int startRow = getRowIndex(startHour);
            int endRow = getRowIndex(endHour);

            for (int i = startRow; i < endRow; i++) {
                //int dayOfWeek = aula.getDataAula().getDiaSemana().getValue();
                model.setValueAt(eventName, i, aula.getDataAula().getDiaSemana().ordinal()+1);
            }
        }

        // Customize the look and feel of the table
        TableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        ((DefaultTableCellRenderer) centerRenderer).setHorizontalAlignment(SwingConstants.CENTER);
        calendarTable.setDefaultRenderer(Object.class, centerRenderer);
        calendarTable.getTableHeader().setBackground(Color.white);
        calendarTable.getTableHeader().setReorderingAllowed(false);
        calendarTable.setGridColor(Color.black);
        calendarTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        calendarTable.getColumnModel().getColumn(0).setPreferredWidth(50);

        // Add the table to a JPanel
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(calendarTable.getTableHeader(), BorderLayout.PAGE_START);
        panel.add(calendarTable, BorderLayout.CENTER);

        // Add the JPanel to a JFrame
        this.getContentPane().add(new JScrollPane(panel));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setVisible(true);
    }

    // Method to get the row index for a given time
    private int getRowIndex(LocalTime time) {
        int hour = time.getHour();
        int minute = time.getMinute();
        if (hour == 8 && minute == 0) {
            return 1;
        } else if (hour == 9 && minute == 30) {
            return 2;
        } else if (hour == 11 && minute == 0) {
            return 3;
        } else if (hour == 12 && minute == 30) {
            return 4;
        } else if (hour == 13 && minute == 0) {
            return 5;
        } else if (hour == 14 && minute == 30) {
            return 6;
        } else if (hour == 16 && minute == 0) {
            return 7;
        } else if (hour == 17 && minute == 30) {
            return 8;
        } else {
            throw new IllegalArgumentException("Invalid time interval");
        }
    }
}
