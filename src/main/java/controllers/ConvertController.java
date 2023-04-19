package controllers;

import gui.App;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.FileConverter;
import utils.ImportFileReader;

import javax.swing.*;
import java.io.File;

public class ConvertController extends ViewController{

    private static final Logger logger = LoggerFactory.getLogger(ConvertController.class);

    public ConvertController(App app) {
        super(app);
        logger.info("- inicializado com sucesso.");
    }


    /**
     * Função que faz a conversão de CSV para JSON
     */
    public void convertCSVtoJSON(){
        if(isFileUploaded()){
            logger.info("Conversão CSV to JSON");
            ImportFileReader csvReader = new ImportFileReader();
            setHorario(csvReader.csvToHorario(getHorario().getFile()));
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showSaveDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File fileTo = fileChooser.getSelectedFile();
                FileConverter.convertCSVTOJSON(getHorario().getFile(), fileTo);
            }
            //logger.debug(getHorario().toString());
        }else{
            JOptionPane.showMessageDialog(contentPane, "Por favor faça upload de um ficheiro primeiro!",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    /**
     * Função que faz a conversão de JSON para CSV
     *
     */
    public void convertJSONtoCSV(){
        if(isFileUploaded()){
            ImportFileReader csvReader = new ImportFileReader();
            setHorario(csvReader.csvToHorario(getHorario().getFile()));
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showSaveDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File fileTo = fileChooser.getSelectedFile();
                FileConverter.convertCSVTOJSON(getHorario().getFile(), fileTo);
            }
        }else{
            JOptionPane.showMessageDialog(contentPane, "Por favor faça upload de um ficheiro primeiro!",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }

    }
}
