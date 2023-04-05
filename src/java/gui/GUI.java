package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Classe GUI que é o GUI Java que interage com o utilizador final
 */
public class GUI {

    private final JFrame frame;
    private Container contentPanel;

    /**
     * Cria uma nova instância da classe GUI.
     *
     * @param title o título da janela da interface gráfica.
     */
    public GUI(String title) {
        frame = new JFrame(title);
    }

    /**
     * Inicializa a interface gráfica do utilizador.
     */
    public void init(){

        // Inicializa o painel de conteudo
        contentPanel = frame.getContentPane();

        showMainMenu();

        // Define o tamanho da janela
        frame.setSize(400, 300);

        // Define o comportamento da janela quando fechar
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Define a janela como visível
        frame.setVisible(true);


    }


    /**
     * Função que mostra o menu principal
     */
    protected void showMainMenu(){
        // Cria o painel para os botões
        JPanel panel = new JPanel();

        // Cria os botões e adiciona-os ao painel
        JButton importBtn = new JButton("Importar horário");

        panel.add(importBtn);

        // Adiciona o painel ao contentor principal da janela
        contentPanel.add(panel, BorderLayout.CENTER);

        // Configura ação do botão de importar
        importBtn.addActionListener(new ActionListener() {
            /**
             * Mostra o menu de import de ficheiros
             * @param e the event to be processed
             */
            public void actionPerformed(ActionEvent e) {
                showImportMenu();
            }
        });
    }



    protected void showImportMenu(){
        contentPanel.removeAll();

        // Cria o painel para os botões
        JPanel panel = new JPanel();

        // Cria os botões e adiciona-os ao painel
        JButton localBtn = new JButton("Ficheiro local");
        JButton remoteBtn = new JButton("Ficheiro online");

        panel.add(localBtn);
        panel.add(remoteBtn);

        // Adiciona o painel ao contentor principal da janela
        contentPanel.add(panel, BorderLayout.CENTER);

        contentPanel.revalidate();

        // Configura ação do botão de importar
        localBtn.addActionListener(new ActionListener() {
            /**
             * Mostra o menu de import de ficheiros
             * @param e the event to be processed
             */
            public void actionPerformed(ActionEvent e) {
                importFile();
            }
        });

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

    /**
     * Importa o calendário
     */
    protected void importFile(){
        // Abrir um seletor de ficheiros
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("CSV and JSON Files", "csv", "json"));
        int result = fileChooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            // Obter o ficheiro selecionado
            File selectedFile = fileChooser.getSelectedFile();

            // TODO: Adicionar lógica para importar o ficheiro
        }
    }



}
