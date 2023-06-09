package controllers;

import gui.App;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import utils.exporter.FileExporter;

import javax.swing.*;

/**
 * Esta classe é um controlador para a conversão de ficheiros CSV para o formato JSON.
 * Estende a classe ViewController.
 */
public class ConvertController extends ViewController {

    private static final Logger logger = LoggerFactory.getLogger(ConvertController.class);

    /**
     * Construtor da classe ConvertController.
     * @param app O objeto principal da aplicação.
     */
    public ConvertController(App app) {
        super(app);
        logger.info("- inicializado com sucesso.");
    }

    /**
     * Função que faz transforma o objeto Horário para uma String
     * esta String pode então ser convertida para qualquer formato
     * @see FileExporter
     * @see ViewController
     */
    public void convertFile(){
        if(isHorarioSet()){
            try{
                String content = FileExporter.convertHorarioToFormat(getHorario());
                setContent(content);
                showExportFilesView();
            }catch (IllegalArgumentException e){
                JOptionPane.showMessageDialog(contentPane, "O ficheiro não tem um formato válido",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }

        }else{
            JOptionPane.showMessageDialog(contentPane, "Por favor faça upload de um ficheiro primeiro!",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

}
