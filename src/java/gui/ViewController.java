package gui;

import utils.FileDownloader;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;

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
            //TODO chamar funcao de converter CSV para JSON
            System.out.println(uploadedFile);
            System.out.println("Conversão CSV to JSON");
        }else{
            JOptionPane.showMessageDialog(contentPane, "Por favor faça upload de um ficheiro primeiro!",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void convertJSONtoCSV(){
        if(isFileUploaded()){
            //TODO chamar funcao de converter JSON para CSV
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
        uploadedFile = FileDownloader.downloadRemoteFile();
        if(isFileUploaded()){
            System.out.println(uploadedFile);
        }
    }

    // add methods for other views as needed
}
