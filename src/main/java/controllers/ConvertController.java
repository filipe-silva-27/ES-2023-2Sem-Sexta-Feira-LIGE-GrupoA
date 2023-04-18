package controllers;

import gui.App;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.CSVFileReader;

import javax.swing.*;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.lang.management.ThreadMXBean;

public class ConvertController extends ViewController{

    private static final Logger logger = LoggerFactory.getLogger(ConvertController.class);

    public ConvertController(App app) {
        super(app);
        logger.info("- inicializado com sucesso.");
    }


    /**
     * Função que faz a conversão de CSV para JSON
     * @deprecated
     */
    public void convertCSVtoJSON(){
        //TODO chamar convert(uploadedFile); ~Torgo
        if(isFileUploaded()){
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
        }else{
            JOptionPane.showMessageDialog(contentPane, "Por favor faça upload de um ficheiro primeiro!",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    /**
     * Função que faz a conversão de JSON para CSV
     * @deprecated
     */
    public void convertJSONtoCSV(){
        if(isFileUploaded()){
            //TODO chamar funcao de converter JSON para CSV
            logger.info("Conversão JSON para CSV");
        }else{
            JOptionPane.showMessageDialog(contentPane, "Por favor faça upload de um ficheiro primeiro!",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }

    }
}
