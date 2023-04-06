package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.io.*;
import javax.swing.*;

/**
 * Classe Home que é o da página principal Java que interage com o utilizador final
 * @author Mário Cao
 */

public class Home extends GUI{

    private final Container contentPanel;
    /**
     * Cria uma nova instância da classe GUI.
     */

    private final JFrame frame;
    public Home(String title) {
        super(title);
        frame = getFrame();
        contentPanel = getContentPanel();
        this.init();
    }

    /**
     * Inicializa a interface gráfica do utilizador.
     */
    public void init(){
        showMainMenu();
    }


    /**
     * Função que mostra o menu principal
     */
    protected void showMainMenu(){
        // Cria o painel para os botões
        JPanel panel = new JPanel();

        // adiciona os botoes ao painel
        panel.add(super.importBtn);
        panel.add(super.criarBtn);

        // Adiciona o painel ao contentor principal da janela
        contentPanel.add(panel, BorderLayout.CENTER);

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
