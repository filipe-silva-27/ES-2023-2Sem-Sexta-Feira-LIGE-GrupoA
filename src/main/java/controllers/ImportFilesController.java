package controllers;

import gui.App;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.importer.FileDownloader;
import utils.importer.ImportFileReader;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.io.IOException;

/**
 * Esta classe é um controlador para importar para a aplicação ficheiros CSV e JSON.
 * Estende a classe ViewController.
 */
public class ImportFilesController extends ViewController{

    private static final Logger logger = LoggerFactory.getLogger(ImportFilesController.class);

    /**
     * Construtor do controlador de upload de arquivos.
     * @param app - instância da classe principal da aplicação
     */
    public ImportFilesController(App app) {
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
            File fromFile = fileChooser.getSelectedFile();
            importFile(fromFile);
        }
    }

    /**
     * Função que trata do import de ficheiro remoto.
     */
    public void importRemoteFile()  {
        // Import a Webcal URI
        importWebcalURI();
    }

    /**
     * Função que faz o import do WebcalURI
     */
    public void importWebcalURI(){
        // Display a dialog box to prompt the user to enter the Webcal URI
        String input = JOptionPane.showInputDialog(getApp(), "Enter the Webcal URI:");
        if (input != null && !input.trim().isEmpty()) {
            try {
                // Parse the input as a URI
                URI webcalURI = new URI(input);

                // Do something with the URI, such as downloading the calendar events
                // or adding them to the application's calendar

                // For example, you could use the following code to display the calendar
                // events in a dialog box:
                JOptionPane.showMessageDialog(getApp(), "Calendar events imported from:\n" + webcalURI.toString());

            } catch (URISyntaxException e) {
                logger.error("Error importing Webcal URI", e);
                JOptionPane.showMessageDialog(getApp(), "Error importing Webcal URI:\n" + e.getMessage());
            }
        }

    }

    /**
     * Método que realiza a importação de um arquivo.
     * @param fromFile - arquivo de origem a ser importado
     */
    void importFile(final File fromFile) {
        if (fromFile == null) {
            showErrorAndUploadView("Por favor escolha um ficheiro válido!");
        } else {
            String fileName = fromFile.getName();
            ImportFileReader reader = new ImportFileReader();
            if (fileName.endsWith(".csv")) {
                logger.debug("{}", fileName);
                setHorario(reader.csvToHorario(fromFile));
            } else if (fileName.endsWith(".json")) {
                logger.debug("{}", fileName);
                setHorario(reader.jsonToHorario(fromFile));
            } else {
                showErrorAndUploadView("Por favor escolha um ficheiro válido!");
                return;
            }
            getHorario().setFile(fromFile);
            showMainMenuView();
        }
    }

    /**
     * Método que exibe uma mensagem de erro e volta à view de upload de arquivos.
     * @param message - mensagem de erro a ser exibida
     */
    private void showErrorAndUploadView(String message) {
        JOptionPane.showMessageDialog(null, message, "Erro", JOptionPane.ERROR_MESSAGE);
        showImportFilesView();
    }

}
