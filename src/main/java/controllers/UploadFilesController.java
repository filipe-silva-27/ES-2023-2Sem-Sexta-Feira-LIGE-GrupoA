package controllers;

import gui.App;
import models.Horario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.CSVFileReader;
import utils.FileDownloader;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.lang.management.ThreadMXBean;

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
        //TODO só aceitar CSV para o converter CSV to JSON
        //TODO só aceitar JSON para o converter JSON to CSV
        // Abrir um seletor de ficheiros
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("CSV and JSON Files", "csv", "json"));
        int result = fileChooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            // Obter o ficheiro selecionado
            //TODO alterar
            setHorario(new Horario("H1"));
            getHorario().setFile(fileChooser.getSelectedFile());
            if(isFileUploaded()){
                logger.debug(String.valueOf(getHorario().getFile()));
                //TODO chamar funcao de converter CSV para JSON
                logger.info("Conversão CSV to JSON");
                CSVFileReader csvReader = new CSVFileReader();
                setHorario(csvReader.CSVtoHorario(getHorario().getFile()));
                // debug logger
                MemoryMXBean memBean = ManagementFactory.getMemoryMXBean();
                MemoryUsage heapUsage = memBean.getHeapMemoryUsage();
                ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
                long cpuTime = threadMXBean.getCurrentThreadCpuTime() / 1_000_000; // convert to milliseconds
                logger.debug("\n\n\nFinished setting Horario Object");
                logger.debug("Memory usage: " + heapUsage.getUsed() / (1024 * 1024) + "MB");
                logger.debug("CPU time: " + cpuTime + "ms");
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
        if(isFileUploaded()){
            logger.debug(String.valueOf(getHorario().getFile()));
            //TODO chamar funcao de converter CSV para JSON
            logger.info("Conversão CSV to JSON");
            CSVFileReader csvReader = new CSVFileReader();
            setHorario(csvReader.CSVtoHorario(getHorario().getFile()));
            // debug logger
            MemoryMXBean memBean = ManagementFactory.getMemoryMXBean();
            MemoryUsage heapUsage = memBean.getHeapMemoryUsage();
            ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
            long cpuTime = threadMXBean.getCurrentThreadCpuTime() / 1_000_000; // convert to milliseconds
            logger.debug("\n\n\nFinished setting Horario Object");
            logger.debug("Memory usage: " + heapUsage.getUsed() / (1024 * 1024) + "MB");
            logger.debug("CPU time: " + cpuTime + "ms");
            showMainMenuView();
        }else{
            showUploadFilesView();
        }

    }

}
