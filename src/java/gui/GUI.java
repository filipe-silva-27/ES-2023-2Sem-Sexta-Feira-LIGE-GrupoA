package gui;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Classe GUI que é o GUI Java que interage com o utilizador final
 * @author Mário Cao
 */
public class GUI {

    private final JFrame frame;
    private Container contentPanel;

    /**
     * Cria uma nova instância da classe GUI.
     * @author Mário Cao
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

        importBtn.addActionListener(new ActionListener() {
            /**
             * Mostra o sub-menu de import de ficheiros
             * @param e the event to be processed
             */
            public void actionPerformed(ActionEvent e) {
                showImportMenu();
            }
        });
    }


    /**
     * Sub-menu de import de ficheiros
     */
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

        localBtn.addActionListener(new ActionListener() {
            /**
             * Mostra o menu de import de ficheiros locais
             * @param e the event to be processed
             */
            public void actionPerformed(ActionEvent e) {
                importLocalFile();
            }
        });


        remoteBtn.addActionListener(new ActionListener() {
            /**
             * Mostra o menu de import de ficheiros
             * @param e the event to be processed
             */
            public void actionPerformed(ActionEvent e) {
                //TODO implementacao da funcionalidade de import de ficheiros da web (remotamente)
                try{
                    importRemoteFile();
                }catch (IOException ioException){
                    //TODO Tratar excecao
                    JPopupMenu errorPopup = new JPopupMenu("Erro na busca do ficheiro remoto!");
                }

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
     * Função que trata do import de ficheiro remoto.
     */
    protected void importRemoteFile() throws IOException {
        // Pede ao utilizador para inserir a URL do ficheiro
        String url = JOptionPane.showInputDialog(frame, "Insira a URL do ficheiro:");

        if (url != null && !url.isEmpty()) {
            // Cria um objeto URL a partir da string inserida
            URL remoteFile = new URL(url);

            // Cria um objeto File para o destino local
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showSaveDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                // Obter o ficheiro selecionado
                File localFile = fileChooser.getSelectedFile();

                // Obtém a extensão do arquivo remoto
                String extension = FilenameUtils.getExtension(remoteFile.getPath());

                // Adiciona a extensão ao arquivo local se ela não estiver presente
                if (!extension.isEmpty() && !localFile.getName().endsWith(extension)) {
                    localFile = new File(localFile.getAbsolutePath() + "." + extension);
                }

                // Faz o download do ficheiro remoto e guarda-o no ficheiro local
                FileUtils.copyURLToFile(remoteFile, localFile);

                // Verifica se o arquivo é CSV ou JSON
                String contentType = Files.probeContentType(localFile.toPath());
                if (contentType == null || (!contentType.equals("text/csv") && !contentType.equals("application/json"))) {
                    JOptionPane.showMessageDialog(frame, "O ficheiro importado não é um ficheiro CSV ou JSON válido.");
                    localFile.delete();
                }
            }
        }
    }



    /**
     * Função que trata do import do ficheiro de uma pasta local
     * Só aceita ficheiros do tipo CSV e JSON.
     */
    protected void importLocalFile(){
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
