package gui;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;

public class ImportMenu {
    /**
     * Sub-menu de import de ficheiros recebe a janela e o contentor da janela Main Menu
     */
    private final GUI gui;
    private final JFrame frame;
    private final Container contentPanel;

    public ImportMenu(String title) {
        System.out.println("Home: " + title);
        gui = GUI.getInstance();
        frame = gui.getFrame();
        frame.setName(title);
        contentPanel = gui.getContentPanel();
        this.init();
    }

    /**
     * Inicializa o menu de import de ficheiros
     */
    public void init(){

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

        //cria painel para botoes de redirecionamento entre menus
        JPanel redirectPanel = new JPanel();

        //adiciona botoes de redirecionamento
        redirectPanel.add(gui.convertBtn);
        redirectPanel.add(gui.visualizarBtn);

        contentPanel.add(redirectPanel, BorderLayout.SOUTH);


        contentPanel.revalidate();

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
            //devolver p torgo
        }
    }

}
