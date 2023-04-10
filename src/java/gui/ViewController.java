package gui;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;

public class ViewController {
    private final CardLayout cardLayout;
    private final JFrame frame;
    private final JPanel contentPane;
    private File uploadedFile = null;

    public ViewController(JPanel contentPane, JFrame frame) {
        this.cardLayout = (CardLayout) contentPane.getLayout();
        this.contentPane = contentPane;
        this.frame = frame;
    }

    public void showMainMenuView() {
        cardLayout.show(contentPane, "mainMenuView");
    }

    public void showUploadFilesView(){
        cardLayout.show(contentPane,"uploadFilesView");
    }

    public boolean isFileUploaded(){
        return uploadedFile != null;
    }

    public void convertCSVtoJSON(){
        //convert(uploadedFile);
        if(isFileUploaded()){
            System.out.println(uploadedFile);
            System.out.println("Conversão CSV to JSON");
        }else{
            JOptionPane.showMessageDialog(contentPane, "Por favor faça upload de um ficheiro primeiro!",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void convertJSONtoCSV(){
        //convert(uploadedFile);
        if(isFileUploaded()){
            System.out.println(uploadedFile);
            System.out.println("Conversão JSON para CSV");
        }else{
            JOptionPane.showMessageDialog(contentPane, "Por favor faça upload de um ficheiro primeiro!",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }

    }


    public void importLocalFile() {
        //TODO só aceitar CSV para o converter CSV to JSON
        //TODO só aceitar JSON para o converter JSON to CSV
        // Abrir um seletor de ficheiros
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("CSV and JSON Files", "csv", "json"));
        int result = fileChooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            // Obter o ficheiro selecionado
            uploadedFile = fileChooser.getSelectedFile();
            if(isFileUploaded()){
                //TODO Mostrar view de menu
                showMainMenuView();
            }
        }
    }

    /**
     * Função que trata do import de ficheiro remoto.
     */
    public void importRemoteFile()  {
        // Pede ao utilizador para inserir a URL do ficheiro
        String url = JOptionPane.showInputDialog(frame, "Insira a URL do ficheiro:");

        if (url != null && !url.isEmpty()) {
            // Cria um objeto URL a partir da string inserida
            URL remoteFile = null;
            try {
                remoteFile = new URL(url);
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }

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

                try{
                    // Faz o download do ficheiro remoto e guarda-o no ficheiro local
                    FileUtils.copyURLToFile(remoteFile, localFile);

                    // Verifica se o arquivo é CSV ou JSON
                    String contentType = Files.probeContentType(localFile.toPath());
                    if (contentType == null || (!contentType.equals("text/csv") && !contentType.equals("application/json"))) {
                        JOptionPane.showMessageDialog(frame, "O ficheiro importado não é um ficheiro CSV ou JSON válido.");
                        localFile.delete();
                    }
                }catch (IOException e){
                    throw new RuntimeException();
                }
            }
        }
    }

    // add methods for other views as needed
}
