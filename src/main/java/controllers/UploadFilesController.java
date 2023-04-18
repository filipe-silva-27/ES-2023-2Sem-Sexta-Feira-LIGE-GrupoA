package controllers;

import gui.App;
import models.Horario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.FileDownloader;
import utils.ImportFileReader;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class UploadFilesController extends ViewController{

    private static final Logger logger = LoggerFactory.getLogger(UploadFilesController.class);

    public UploadFilesController(App app) {
        super(app);
        logger.info("- inicializado com sucesso.");
    }

    /**
     * Função que trata do carregamento do ficheiro local
     */
    public void importLocalFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("CSV and JSON Files", "csv", "json"));
        int result = fileChooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            // Obter o ficheiro selecionado
            setHorario(new Horario("H1"));
            getHorario().setFile(fileChooser.getSelectedFile());
            //TODO chamar a funcao correspondente, caso seja CSV ou JSON
            if(isFileUploaded()){
                logger.debug(String.valueOf(getHorario().getFile()));
                ImportFileReader importFileReader = new ImportFileReader();
                setHorario(importFileReader.csvToHorario(getHorario().getFile()));
                showMainMenuView();
            }else{
                showUploadFilesView();
            }
        }
    }

    /**
     * Função que trata do import de ficheiro remoto.
     */
    public void importRemoteFile()  {
        //TODO alterar
        setHorario(new Horario(" "));
        getHorario().setFile(FileDownloader.downloadRemoteFile());
        //TODO chamar a funcao correspondente, caso seja CSV ou JSON
        if(isFileUploaded()){
            logger.debug(String.valueOf(getHorario().getFile()));
            ImportFileReader importFileReader = new ImportFileReader();
            setHorario(importFileReader.csvToHorario(getHorario().getFile()));
            showMainMenuView();
        }else{
            showUploadFilesView();
        }

    }

}
