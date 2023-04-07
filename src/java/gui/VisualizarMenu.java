package gui;

import javax.swing.*;
import java.awt.*;

public class VisualizarMenu extends Screen {


    public VisualizarMenu(String title) {
        super(title);
    }

    public void init() {

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

        //cria painel para botoes de redirecionamento entre menus
        JPanel redirectPanel = new JPanel();

        //adiciona botoes de redirecionamento
        redirectPanel.add(gui.backBtn);
        redirectPanel.add(gui.exportBtn);
        redirectPanel.add(gui.convertBtn);

        contentPanel.add(redirectPanel, BorderLayout.SOUTH);

        contentPanel.revalidate();
    }
}
