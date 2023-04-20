package views;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import models.Aula;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CalendarView extends JFrame {
    private transient List<Aula> aulas;
    private LocalDate startDate;
    private JLabel weekLabel = new JLabel();
    private JTable calendarTable;
    private JPanel panel;
    private final transient DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd");
    public static final int NUM_INTERVALS = 28;
    private static final Logger logger = LoggerFactory.getLogger(CalendarView.class);


    public CalendarView(List<Aula> aulas) {
        this.aulas = aulas;
        this.startDate = this.aulas.get(0).
                getDataAula().getData().
                toInstant().atZone(ZoneId.systemDefault()).toLocalDate().with(DayOfWeek.MONDAY);
        logger.debug("START DATE: {}\n\n\n", startDate);
        initFrame();
    }

    private DefaultTableModel initTable(){
        // Clear the table
        DefaultTableModel model = (DefaultTableModel) calendarTable.getModel();
        model.setRowCount(0);
        model.setColumnCount(0);

        // Set column headers for the days of the week with dates
        String[] daysOfWeek = {"", startDate.format(formatter), startDate.plusDays(1).format(formatter),
                startDate.plusDays(2).format(formatter), startDate.plusDays(3).format(formatter),
                startDate.plusDays(4).format(formatter), startDate.plusDays(5).format(formatter),
                startDate.plusDays(6).format(formatter)};
        for (int i = 0; i < 7; i++) {
            model.addColumn(daysOfWeek[i]);
        }


        for (int i = 0; i <= NUM_INTERVALS; i++) {
            int hour = 8 + i / 2;
            int minute = (i % 2) * 30;
            String time = String.format("%02d:%02d", hour, minute);
            model.addRow(new Object[]{time});
        }


        return model;
    }

    private void updateTable() {
        DefaultTableModel model = initTable();

        // Add the classes as events to the calendar for the current week
        LocalDate endDate = startDate.plusDays(7);
        weekLabel.setText("Week of " + startDate + " - " + endDate.minusDays(1).format(formatter));
        for (Aula aula : aulas) {
            Date date = aula.getDataAula().getData();
            LocalDate aulaDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            if (aulaDate.isAfter(startDate) && aulaDate.isBefore(endDate)) {
                LocalTime startHour = aula.getDataAula().getHoraInicio();
                LocalTime endHour = aula.getDataAula().getHoraFim();
                String eventName = aula.getUc().getNomeUC();
                int startRowIndex = getRowIndex(startHour);
                int endRowIndex = getRowIndex(endHour);
                for (int i = startRowIndex; i <= endRowIndex; i++) {
                    int columnIndex = aula.getDataAula().getDiaSemana().ordinal() + 1;
                    logger.debug("UC: {} | DataAula: {} | HoraInicio: {} | Fim: {} | startRowIndex: {} | endRowIndex: {} | columnIndex: {}",
                            eventName, aulaDate,startHour, endHour, startRowIndex, endRowIndex, columnIndex);
                    Object currentValue = model.getValueAt(i, columnIndex);
                    if (currentValue == null) {
                        logger.debug("Criando evento: {}", eventName);
                        model.setValueAt(aula, i,columnIndex);
                        TableColumn aulaColumn = calendarTable.getColumnModel().getColumn(columnIndex); // Replace aulaColumnIndex with the column index of the Aula object in your table model
                        aulaColumn.setCellRenderer(new AulaTableCellRenderer());

                    } else {
                        logger.debug("Sobreposição!");
                        model.setValueAt(aula, i, columnIndex);
                    }
                }
            }
        }

    }

    private int getRowIndex(LocalTime time) {
        // Get the starting time of the first row (8:00 AM)
        LocalTime startTime = LocalTime.of(8, 0);

        // Calculate the time difference between the starting time and the given time
        Duration duration = Duration.between(startTime, time);

        // Calculate the number of half-hour blocks since the starting time
        long blocks = duration.toMinutes() / 30;

        // Return the corresponding row index, or -1 if the time is outside the table bounds
        return (blocks >= 0 && blocks < NUM_INTERVALS) ? (int)blocks : -1;
    }


    private void setButtons(){
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
    }


    private void initFrame() {

        // Create a table to display the calendar
        DefaultTableModel model = new DefaultTableModel();
        calendarTable = new JTable(model);
        calendarTable.setRowHeight(60);

        updateTable();

        // Add the table to a JPanel
        panel = new JPanel(new BorderLayout());

        setButtons();


        // Customize the look and feel of the table
        TableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        ((DefaultTableCellRenderer) centerRenderer).setHorizontalAlignment(SwingConstants.CENTER);
        calendarTable.setDefaultRenderer(Object.class, centerRenderer);
        calendarTable.getTableHeader().setBackground(Color.white);
        calendarTable.getTableHeader().setReorderingAllowed(false);
        calendarTable.setGridColor(Color.black);
        calendarTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        calendarTable.getColumnModel().getColumn(0).setPreferredWidth(75);

        panel.add(calendarTable.getTableHeader(), BorderLayout.PAGE_START);
        panel.add(calendarTable, BorderLayout.CENTER);

        calendarTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row =calendarTable.getSelectedRow();
                int column = calendarTable.getSelectedColumn();
                Object value = calendarTable.getValueAt(row, column);
                if (value instanceof Aula) {
                    Aula aula = (Aula) value;
                    // Show the details dialog for the selected Aula object
                    JOptionPane.showMessageDialog(null, aula,
                            "Informação da Aula", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });


        panel.repaint();
        panel.revalidate();

        // Add the JPanel to a JFrame
        this.getContentPane().add(new JScrollPane(panel));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setVisible(true);
    }


    public static class AulaTableCellRenderer extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JLabel label = (JLabel) super.getTableCellRendererComponent(table, "", isSelected, hasFocus, row, column);
            if (value instanceof Aula) {
                Aula aula = (Aula) value;
                LocalTime startHour = aula.getDataAula().getHoraInicio();
                LocalTime endHour = aula.getDataAula().getHoraFim();
                String sala = aula.getSala();
                 // Replace with whatever label text you want to display
                String labelText = aula.getUc().getNomeUC() + "\n" + startHour + "\n" + endHour + "\n" + sala;
                label.setText(labelText);
                label.setToolTipText(aula.toString()); // Set tooltip text to show full Aula object details
            } else {
                label.setText("");
                label.setToolTipText("");
            }
            return label;
        }
    }

}
