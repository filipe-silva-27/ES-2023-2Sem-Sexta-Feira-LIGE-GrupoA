package gui;

import utils.FileDownloader;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;

/**
 * Classe ViewController que é o controlador das views todas que
 * trata da lógica dos botões etc
 */
public class ViewController {

    //TODO - implementar Models numa package models
    private final CardLayout cardLayout;
    private final JFrame frame;
    private final JPanel contentPane;
    private File uploadedFile = null;

    /**
     * Construtor do controlador
     * @param contentPane painel principal da GUI
     * @param frame frame da GUI
     */
    public ViewController(JPanel contentPane, JFrame frame) {
        this.cardLayout = (CardLayout) contentPane.getLayout();
        this.contentPane = contentPane;
        this.frame = frame;
    }

    /**
     * Funções que mudam de view
     */

    /**
     * Função que mostra a view do menu principal
     */
    public void showMainMenuView() {
        if(isFileUploaded()){
            cardLayout.show(contentPane, App.MAIN_MENU_VIEW);
        }else {
            showUploadFilesView();
        }

    }

    /**
     * Função que muda para a view do menu de ficheiros para fazer upload
     */
    public void showUploadFilesView(){
        cardLayout.show(contentPane,App.UPLOAD_MENU_VIEW);
    }


    /**
     * Função que muda para a view do visualizacao de horario
     */
    public void showShowScheduleView() {
        cardLayout.show(contentPane,App.SHOW_SCHEDULE_VIEW);
    }

    /**
     * Função que muda para a view de criação de horario
     */
    public void showCreateScheduleView() {
        cardLayout.show(contentPane,App.CREATE_SCHEDULE_VIEW);
    }

    /**
     * Método que verifica se o ficheiro/horário foi uploaded
     * @return boolean - se o ficheiro foi uploaded ou não
     */
    public boolean isFileUploaded(){
        return uploadedFile != null;
    }

    /**
     * Função que faz a conversão de CSV para JSON
     */
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

    /**
     * Função que faz a conversão de JSON para CSV
     */
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


    /**
     * Função que trata do carregamento do ficheiro local
     */
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

}
