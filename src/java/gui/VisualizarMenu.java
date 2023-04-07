package gui;

import javax.swing.*;
import java.awt.*;

public class VisualizarMenu {

    private final GUI gui;
    private final Container contentPanel;
    private final JFrame frame;

    public VisualizarMenu(String title) {
        gui = GUI.getInstance();
        frame = gui.getFrame();
        frame.setName(title);
        contentPanel = gui.getContentPanel();
        this.init();
    }

    private void init() {

        contentPanel.removeAll();

        // Cria o painel para os botões
        JPanel panel = new JPanel();

        // TODO: @Rui get a table to hold the calendar data
        //JTable calendarData = new JTable();

        // add the table to the calendar panel
        //panel.add(calendarData);

        // redraw the calendar panel
        //panel.revalidate();


        // Adiciona o painel ao contentor principal da janela
        contentPanel.add(panel, BorderLayout.CENTER);

        contentPanel.revalidate();
    }
}
