package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Classe GUI que é o GUI Java que interage com o utilizador final
 */
public class GUI {

    private JFrame frame;

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
        // Cria o painel para os botões
        JPanel panel = new JPanel();

        // Cria os botões e adiciona-os ao painel
        JButton importBtn = new JButton("Importar");
        JButton exportBtn = new JButton("Exportar");

        panel.add(importBtn);
        panel.add(exportBtn);

        // Adiciona o painel ao contentor principal da janela
        Container contentPane = frame.getContentPane();
        contentPane.add(panel, BorderLayout.CENTER);

        // Define o tamanho da janela
        frame.setSize(400, 300);

        // Define o comportamento da janela quando fechar
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Define a janela como visível
        frame.setVisible(true);

        // Configura ação do botão de importar
        importBtn.addActionListener(new ActionListener() {
            /**
             * Executa a ação correspondente ao botão "Importar".
             *
             * @param e o evento de clique do botão "Importar".
             */
            public void actionPerformed(ActionEvent e) {
                importFile();
            }
        });

        // Configura ação do botão de exportar
        exportBtn.addActionListener(new ActionListener() {
            /**
             * Executa a ação correspondente ao botão "Exportar".
             *
             * @param e o evento de clique do botão "Exportar".
             */
            public void actionPerformed(ActionEvent e) {
                exportFile();
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
        int result = fileChooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            // Obter o ficheiro selecionado
            File selectedFile = fileChooser.getSelectedFile();

            // TODO: Adicionar lógica para importar o ficheiro
        }
    }


}
