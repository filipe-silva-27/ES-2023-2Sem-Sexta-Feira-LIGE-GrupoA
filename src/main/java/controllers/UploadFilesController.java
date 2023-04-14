package controllers;

import gui.App;
import models.Schedule;
import utils.FileDownloader;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class UploadFilesController extends ViewController{

    public UploadFilesController(App app) {
        super(app);
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
            app.setSchedule(new Schedule(" ", 7));
            app.getSchedule().setFile(fileChooser.getSelectedFile());
            if(isFileUploaded()){
                //TODO Mostrar view de menu
                cardLayout.show(contentPane, App.MAIN_MENU);
            }
        }
    }

    /**
     * Função que trata do import de ficheiro remoto.
     */
    public void importRemoteFile()  {
        //schedule.
        app.getSchedule().setFile(FileDownloader.downloadRemoteFile());
        if(isFileUploaded()) {
            // Mostrar view de menu
            cardLayout.show(contentPane, App.MAIN_MENU);
        }
    }

}
