package controllers;

import gui.App;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.uploader.FileUploader;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Esta classe é um controlador para exportar conteúdo.
 * Estende a classe ViewController.
 */
public class ExportController extends ViewController {

    private static final String ERROR_MSG = "Erro";

    private static final Logger logger = LoggerFactory.getLogger(ExportController.class);

    /**
     * Construtor da classe ExportController.
     * @param app O objeto principal da aplicação.
     */
    public ExportController(final App app) {
        super(app);
        logger.info("- inicializado com sucesso.");
    }

    /**
     * Exporta o conteúdo para armazenamento local.
     */
    public void exportToLocal() {
        if(!isContentSet()){
            JOptionPane.showMessageDialog(null, "Não existe contéudo para exportar!",
                    ERROR_MSG, JOptionPane.ERROR_MESSAGE);
        }
        String content = getContent();
        if(isHorarioSet()){
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showSaveDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File fileTo = fileChooser.getSelectedFile();
                try (FileWriter writer = new FileWriter(fileTo)) {
                    writer.write(content);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Exporta o conteúdo para um servidor remoto.
     */
    public void exportToRemote() {
        if(!isContentSet()){
            JOptionPane.showMessageDialog(null, "Não existe contéudo para exportar!",
                    ERROR_MSG, JOptionPane.ERROR_MESSAGE);
        }
        String content = getContent();
        String fileName = JOptionPane.showInputDialog(null,
                "Introduza o nome do ficheiro (com a extensão):");
        if(fileName == null){
            JOptionPane.showMessageDialog(null, "Nome do ficheiro não pode ficar vazio!",
                    ERROR_MSG, JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("Nome do ficheiro vazio!");
        }
        try {
            String url = FileUploader.exportToGist(fileName, content);
            JOptionPane.showMessageDialog(null, "Sucesso no upload no link: " + url,
                    "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
        }catch (IOException e){
            logger.error("Failed to upload to Gist: {}", e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro no upload para o GitHub GIST",
                    ERROR_MSG, JOptionPane.ERROR_MESSAGE);
        }
    }

}
