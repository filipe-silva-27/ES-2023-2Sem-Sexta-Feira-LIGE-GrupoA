package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConverterMenu {

    private final GUI gui;
    private final JFrame frame;
    private final Container contentPanel;


    public ConverterMenu(String title) {
        gui = GUI.getInstance();
        frame = gui.getFrame();
        frame.setName(title);
        contentPanel = gui.getContentPanel();
        this.init();
    }

    /**
     * Inicializa o menu de import de ficheiros
     */

    public void init() {
        contentPanel.removeAll();

        // Cria o painel para os botões
        JPanel panel = new JPanel();

        // Cria os botões e adiciona-os ao painel
        JButton convertToJsonBtn = new JButton("Converter para JSON");
        JButton convertToCSVBtn = new JButton("Converter para CSV");

        panel.add(convertToJsonBtn);
        panel.add(convertToCSVBtn);

        // Adiciona o painel ao contentor principal da janela
        contentPanel.add(panel, BorderLayout.CENTER);

        convertToJsonBtn.addActionListener(new ActionListener() {
            /**
             * Mostra o menu de import de ficheiros locais
             *
             * @param e the event to be processed
             */
            public void actionPerformed(ActionEvent e) {
                //TODO: @Francisco, chamar aqui a conversão para JSON
            }
        });
        convertToCSVBtn.addActionListener(new ActionListener() {
            /**
             * Mostra o menu de import de ficheiros
             *
             * @param e the event to be processed
             */
            public void actionPerformed(ActionEvent e) {
                //TODO: @Francisco, chamar aqui a conversão para CSV
            }
        });

        //cria painel para botoes de redirecionamento entre menus
        JPanel redirectPanel = new JPanel();

        //adiciona botoes de redirecionamento
        redirectPanel.add(gui.exportBtn);
        redirectPanel.add(gui.visualizarBtn);

        contentPanel.add(redirectPanel, BorderLayout.SOUTH);

        contentPanel.revalidate();

    }
}
