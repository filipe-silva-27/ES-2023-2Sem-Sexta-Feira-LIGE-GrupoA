package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.io.*;
import javax.swing.*;

/**
 * Classe Home que é o da página principal Java que interage com o utilizador final
 * @author Mário Cao
 */

public class Home {

    private final GUI gui;
    private final Container contentPanel;
    /**
     * Cria uma nova instância da classe GUI.
     */

    private final JFrame frame;
    public Home(String title) {
        System.out.println("Home: " + title);
        gui = GUI.getInstance();
        frame = gui.getFrame();
        frame.setName(title);
        contentPanel = gui.getContentPanel();
        this.init();
    }

    /**
     * Inicializa a interface gráfica do utilizador.
     */
    public void init(){
        // Cria o painel para os botões
        JPanel panel = new JPanel();

        // adiciona os botoes ao painel
        panel.add(gui.importBtn);
        panel.add(gui.criarBtn);

        // Adiciona o painel ao contentor principal da janela
        contentPanel.add(panel, BorderLayout.CENTER);

        contentPanel.revalidate();
        System.out.println("btns added");
    }


    /**
     * Exporta dados para um ficheiro selecionado pelo utilizador.
     */
    protected void exportFile() {
        // Abrir um seletor de ficheiros
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            // Obter o ficheiro selecionado
            File selectedFile = fileChooser.getSelectedFile();

            // TODO: Adicionar lógica para exportar para o ficheiro
        }
    }





}
