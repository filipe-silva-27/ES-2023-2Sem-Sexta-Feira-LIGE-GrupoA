package controllers;

import gui.App;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.FileDownloader;
import utils.ImportFileReader;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

/**
 * Esta classe é um controlador para upload de ficheiros.
 * Estende a classe ViewController.
 */
public class UploadFilesController extends ViewController{

    private static final Logger logger = LoggerFactory.getLogger(UploadFilesController.class);

    /**
     * Construtor do controlador de upload de arquivos.
     * @param app - instância da classe principal da aplicação
     */
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
            File fromFile = fileChooser.getSelectedFile();
            importFile(fromFile);
        }
    }

    /**
     * Função que trata do import de ficheiro remoto.
     */
    public void importRemoteFile()  {
        File fromFile = FileDownloader.downloadRemoteFile();
        importFile(fromFile);
    }

    /**
     * Método que realiza a importação de um arquivo.
     * @param fromFile - arquivo de origem a ser importado
     */
    private void importFile(final File fromFile) {
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
        showUploadFilesView();
    }

}
