package utils;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import models.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.lang.management.ThreadMXBean;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ImportFileReader {

    private final Horario horario = new Horario("");
    private static final Logger logger = LoggerFactory.getLogger(ImportFileReader.class);


    /**
     * Método que recebe parametros com valores do ficheiro e cria um objeto DataAula
     * @param diaDaSemana dia da semana da aula
     * @param horaInicio hora de inicio da aula
     * @param horaFim hora de fim da aula
     * @param data data da aula
     * @return objeto DataAula com as informações passadas como parâmetro
     */
    private DataAula criaDataAula(String diaDaSemana, String horaInicio, String horaFim, String data) {
        DataAula dataAula = null;

        try {
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            Date dataObject = new SimpleDateFormat("dd/MM/yyyy").parse(data);
            horaInicio = horaInicio.length() == 7 ? "0" + horaInicio : horaInicio;
            horaFim = horaFim.length() == 7 ? "0" + horaFim : horaFim;
            dataAula = new DataAula(
                    DiaSemana.fromName(diaDaSemana),
                    LocalTime.parse(horaInicio, timeFormatter),
                    LocalTime.parse(horaFim, timeFormatter),
                    dataObject
            );
        } catch (ParseException e) {
            logger.error("Erro na conversão da data da aula", e);
        }

        return dataAula;
    }

    /**
     * Método que recebe parametros com valores do ficheiro e cria um objeto UnidadeCurricular, adicionando à lista de Unidaddes Curriculares do horário
     * @param curso curso da unidade curricular
     * @param unidadeCurricular nome da unidade curricular
     * @return objeto UnidadeCurricular com as informações passadas como parâmetro
     */
    public UnidadeCurricular criaUC(String curso, String unidadeCurricular){
        UnidadeCurricular uc = new UnidadeCurricular(curso, unidadeCurricular);
        if(!horario.addUnidadeCurricular(uc)){
            uc = horario.getUnidadeCurricular(uc);
        }
        logger.debug("UCs: " + horario.getUnidadesCurriculares());
        return uc;
    }

    /**
     * Método que recebe parametros com valores do ficheiro e cria os objetos necessários para criar um horário
     * @param unidadeCurricular nome da unidade curricular
     * @param curso             curso da unidade curricular
     * @param turno             turno da aula
     * @param turma             turma da aula
     * @param diaDaSemana       dia da semana da aula
     * @param horaInicio        hora de inicio da aula
     * @param horaFim           hora de fim da aula
     * @param data              data da aula
     * @param sala              sala da aula
     * @param inscritos         número de inscritos na aula
     * @param lotacao           lotação da aula
     */
    private void criaHorario (String unidadeCurricular, String curso, String turno,
                              String turma, String diaDaSemana, String horaInicio,
                              String horaFim, String data,  String sala,
                              Integer inscritos, Integer lotacao) {
        UnidadeCurricular uc = criaUC(curso, unidadeCurricular);
        Aula aula = new Aula(uc,turno, turma, inscritos, sala, lotacao);
        DataAula dataAula = criaDataAula(diaDaSemana, horaInicio, horaFim, data);
        aula.setDataAula(dataAula);
        uc.addAula(aula);
    }


    /**
     * Método auxiliar que cria objeto UnidadeCurricular por cada linha CSV
     * @param recrd Linha de CSV
     */
    private void processRecord(String[] recrd) {
        try {
            String unidadeCurricular = recrd[1];
            String curso = recrd[0];
            String turno = recrd[2];
            String turma = recrd[3];
            String diaDaSemana = recrd[5];
            String horaInicio = recrd[6];
            String horaFim = recrd[7];
            String data = recrd[8];
            String sala = recrd[9];

            Integer inscritos = Integer.parseInt(recrd[4]);
            Integer lotacao = Integer.parseInt(recrd[10]);

            if (unidadeCurricular.equals("") || horaInicio.equals("") || horaFim.equals("")
                    || data.equals("") || diaDaSemana.equals("")) return;

            criaHorario(unidadeCurricular, curso, turno, turma, diaDaSemana, horaInicio, horaFim, data, sala, inscritos, lotacao);
        } catch (Exception e) {
            logger.error(String.valueOf(e));
        }
    }


    /**
     * Método que lê um ficheiro JSON e cria um horário preenchendo os campos com as informações do ficheiro
     * @param fileCSV ficheiro CSV a ser lido
     * @return horário preenchido com as informações do ficheiro
     */
    public Horario csvToHorario(File fileCSV) {
        try (FileReader reader = new FileReader(fileCSV);
             CSVReader csvReader = new CSVReader(reader)) {
            String[] recrd;
            csvReader.readNext(); // skip header
            while ((recrd = csvReader.readNext()) != null) {
                processRecord(recrd);
            }
            logger.info("Lines read: {}",csvReader.getLinesRead());

            // debug logger
            memoryDebug();
            fileConvertedDebug();
        } catch (IOException | CsvValidationException e) {
            logger.error("Error reading CSV file: {}", e.getMessage());
        }
        return horario;
    }


    /**
     * Método que lê um ficheiro JSON e cria um horário preenchendo os campos com as informações do ficheiro
     * @param jsonFile ficheiro JSON a ser lido
     * @return horário preenchido com as informações do ficheiro
     */
    public Horario jsonToHorario(File jsonFile) {
        try (FileReader reader = new FileReader(jsonFile)) {
            Object o = new JSONParser().parse(reader);

            JSONArray jArray = (JSONArray) o;

            for (Object doc : jArray) {

                JSONObject jsonDoc = (JSONObject) doc;

                String curso = (String) jsonDoc.get("﻿Curso");
                String unidadeCurricular = (String) jsonDoc.get("Unidade Curricular");
                String turno = (String) jsonDoc.get("Turno");
                String turma = (String) jsonDoc.get("Turma");
                Integer inscritos = ((Long) jsonDoc.get("Inscritos no turno")).intValue();
                String diaDaSemana = (String) jsonDoc.get("Dia da semana");
                String horaInicio = (String) jsonDoc.get("Hora inÃ\u00ADcio da aula");
                String horaFim = (String) jsonDoc.get("Hora fim da aula");
                String data = (String) jsonDoc.get("Data da aula");
                String sala = (String) jsonDoc.get("Sala atribuÃ\u00ADda Ã  aula");
                Integer lotacao = ((Long) jsonDoc.get("LotaÃ§Ã£o da sala")).intValue();


                if (unidadeCurricular.equals("") || horaInicio.equals("") || horaFim.equals("")
                        || data.equals("") || diaDaSemana.equals("") ) continue;

                criaHorario(unidadeCurricular, curso, turno, turma, diaDaSemana, horaInicio, horaFim, data, sala, inscritos, lotacao);

            }
            // debug logger
            memoryDebug();
            fileConvertedDebug();
        } catch (org.json.simple.parser.ParseException | IOException ex) {
            logger.error("Error reading JSON file: {}" , ex.getMessage());
        }

        return horario;
    }

    /**
     * Método que imprime o estado da memória e o tempo de CPU foi utilizado pois com ficheiros grandes o programa pode ficar lento
     */
    private void memoryDebug() {
        MemoryMXBean memBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage heapUsage = memBean.getHeapMemoryUsage();
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        long cpuTime = threadMXBean.getCurrentThreadCpuTime() / 1_000_000; // convert to milliseconds
        logger.debug("Memory usage: {}MB", heapUsage.getUsed() / (1024 * 1024));
        logger.debug("CPU time: {}ms", cpuTime);
    }

    private void fileConvertedDebug() {
        logger.debug("File " + horario.getFile() + "| ucs: " + horario.getUnidadesCurriculares().size() + "| aulas: ");
        horario.getUnidadesCurriculares().forEach(uc-> logger.debug("UC tem " + uc.getAulas().size() + " aulas"));
    }
}

