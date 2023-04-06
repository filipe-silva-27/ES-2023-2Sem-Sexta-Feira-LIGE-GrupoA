package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;

/**
 * Classe Home que é o da página principal Java que interage com o utilizador final
 * @author Mário Cao
 */
public class Home {

    private final JFrame frame;
    private Container contentPanel;

    /**
     * Cria uma nova instância da classe GUI.
     * @author Mário Cao
     * @param title o título da janela da interface gráfica.
     */
    public Home(String title) {
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
        JButton criarBtn = new JButton("Criar Horário");

        panel.add(importBtn);
        panel.add(criarBtn);

        // Adiciona o painel ao contentor principal da janela
        contentPanel.add(panel, BorderLayout.CENTER);

        importBtn.addActionListener(new ActionListener() {
            /**
             * Mostra o sub-menu de import de ficheiros
             * @param e the event to be processed
             */
            public void actionPerformed(ActionEvent e) {
                new ImportMenu(frame, contentPanel);
            }
        });
        criarBtn.addActionListener(new ActionListener() {
            /**
             * Mostra o sub-menu de import de ficheiros
             * @param e the event to be processed
             */
            public void actionPerformed(ActionEvent e) {
                //new CriarMenu();
                //FIXME: Criar classe de menu de criar horário
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





}
