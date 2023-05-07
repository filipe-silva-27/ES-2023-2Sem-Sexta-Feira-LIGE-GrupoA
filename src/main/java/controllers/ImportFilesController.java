package controllers;

import gui.App;
import models.CustomExceptions;
import org.junit.Ignore;
import models.Aula;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.importer.FileDownloader;
import utils.importer.ImportFileReader;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;

/**
 * Esta classe é um controlador para importar para a aplicação ficheiros CSV e JSON.
 * Estende a classe ViewController.
 * @see ViewController
 */
public class ImportFilesController extends ViewController{

    private static final Logger logger = LoggerFactory.getLogger(ImportFilesController.class);

    /**
     * Construtor do controlador de upload de arquivos.
     * @param app - instância da classe principal da aplicação
     * @see App
     */
    public ImportFilesController(App app) {
        super(app);
        logger.info("- inicializado com sucesso.");
    }

    /**
     * Função que trata do carregamento do ficheiro local
     */
    @Ignore
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
    @Ignore
    public void importRemoteFile()  {
        File fromFile = null;
        try{
            fromFile = FileDownloader.downloadRemoteFile();
        }catch (CustomExceptions.EmptyUrlException | IOException | CustomExceptions.InvalidFilenameException |
                CustomExceptions.InvalidFileExtensionException e) {
            showErrorAndUploadView(e.getMessage());
        }
        if(fromFile != null){
            importFile(fromFile);
        }

    }

    /**
     * Função que faz o import do WebcalURI
     */

    public void importWebcalURI(){
        // Display a dialog box to prompt the user to enter the Webcal URI

            String input = JOptionPane.showInputDialog(null, "Enter the Webcal URI: ");
            if (input != null && !input.trim().isEmpty()) {

                if (input.startsWith("webcal://")) {
                    input = "https://" + input.substring(9);
                }


                try {
                    setHorario(new ImportFileReader().processaWebcal(input));
                } catch (CustomExceptions.WebcalInvalidFormatException e) {
                    showErrorAndUploadView(e.getMessage());
                }


                JOptionPane.showMessageDialog(null, "Calendar events imported from:\n" + input);
                setIsURI(true);
                showMainMenuView();
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
    @Ignore
    private void showErrorAndUploadView(String message) {
        JOptionPane.showMessageDialog(null, message, "Erro", JOptionPane.ERROR_MESSAGE);
        showImportFilesView();
    }

}
