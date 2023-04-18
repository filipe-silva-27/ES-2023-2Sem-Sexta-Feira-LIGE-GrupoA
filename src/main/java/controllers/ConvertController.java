package controllers;

import gui.App;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.ImportFileReader;

import javax.swing.*;

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
        //TODO chamar convert(uploadedFile); ~Torgo
        if(isFileUploaded()){
            //TODO chamar funcao de converter CSV para JSON
            logger.info("Conversão CSV to JSON");
            ImportFileReader csvReader = new ImportFileReader();
            setHorario(csvReader.csvToHorario(getHorario().getFile()));
            //FileConverter.convertCSVTOJSON(getSchedule().getFile());
            //logger.debug(getHorario().toString());
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
            ImportFileReader csvReader = new ImportFileReader();
            setHorario(csvReader.jsonToHorario(getHorario().getFile()));
            logger.info("Conversão JSON para CSV");
        }else{
            JOptionPane.showMessageDialog(contentPane, "Por favor faça upload de um ficheiro primeiro!",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }

    }
}
