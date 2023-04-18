package utils;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import models.*;
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


    public UnidadeCurricular criaUC(String curso, String unidadeCurricular){
        UnidadeCurricular uc = new UnidadeCurricular(curso, unidadeCurricular);
        if(!horario.addUnidadeCurricular(uc)){
            uc = horario.getUnidadeCurricular(uc);
        }
        return uc;
    }


    public Horario CSVtoHorario (File fileCSV) {
        try (FileReader reader = new FileReader(fileCSV);
             CSVReader csvReader = new CSVReader(reader)){
            String[] headers = csvReader.readNext(); // ler o cabeçalhso
            String[] recrd;
            while ((recrd = csvReader.readNext()) != null) {
                try {
                    //TODO tratar de quando x elemento é vazio e preencher com vazio
                    // processar cada registro aqui
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

                    UnidadeCurricular uc = criaUC(curso, unidadeCurricular);
                    Aula aula = new Aula(turno, turma, inscritos, sala, lotacao);
                    uc.addAula(aula);
                    DataAula dataAula = criaDataAula(diaDaSemana, horaInicio, horaFim, data);
                    aula.setDataAula(dataAula);
                    uc.addAula(aula);

                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
            }
            logger.info("Lines read: " + csvReader.getLinesRead());

            // debug logger
            MemoryMXBean memBean = ManagementFactory.getMemoryMXBean();
            MemoryUsage heapUsage = memBean.getHeapMemoryUsage();
            ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
            long cpuTime = threadMXBean.getCurrentThreadCpuTime() / 1_000_000; // convert to milliseconds
            logger.debug("Memory usage: " + heapUsage.getUsed() / (1024 * 1024) + "MB");
            logger.debug("CPU time: " + cpuTime + "ms");
        } catch (IOException | CsvValidationException e) {
            logger.error("Error reading CSV file: " + e.getMessage());
        }
        return horario;
    }



//    public Horario JSONtoHorario(File jsonFile){
//        try (FileReader reader = new FileReader(jsonFile)){
//            Object o = new JSONParser().parse(reader);
//
//            //debug
//            o = new JSONParser().parse(new FileReader("C:\\Users\\Public\\horarioMiniExemplo.json"));
//
//            JSONArray jArray = (JSONArray) o;
//
//            for (Object doc: jArray) {
//
//                JSONObject jsonDoc = (JSONObject) doc;
//
//                String curso = (String) jsonDoc.get("﻿Curso");
//                String unidadeCurricular = (String) jsonDoc.get("Unidade Curricular");
//                String turno = (String) jsonDoc.get("Turno");
//                String turma = (String) jsonDoc.get("Turma");
//                Integer inscritos = ((Long) jsonDoc.get("Inscritos no turno")).intValue();
//                String diaDaSemana = (String) jsonDoc.get("Dia da semana");
//                String horaInicio = (String) jsonDoc.get("Hora inÃ\u00ADcio da aula");
//                String horaFim = (String)  jsonDoc.get("Hora fim da aula");
//                String data = (String) jsonDoc.get("Data da aula");
//                String sala = (String) jsonDoc.get("Sala atribuÃ\u00ADda Ã  aula");
//                Integer lotacao =((Long) jsonDoc.get("LotaÃ§Ã£o da sala")).intValue();
//
//
//                logger.debug("Curso: " + curso);
//                logger.debug("uc: " + unidadeCurricular);
//                logger.debug("turno: " +turno);
//                logger.debug("inscritos: " + inscritos);
//                logger.debug("dia: " + diaDaSemana);
//                logger.debug("horaIni: " + horaInicio);
//                logger.debug("horaFim: " + horaFim);
//                logger.debug("data: " + data);
//                logger.debug("sala: " + sala);
//                logger.debug("lotacao: " + lotacao);
//
//
//
//                UnidadeCurricular uc = criaUC(unidadeCurricular);
//                criaCursos(uc, curso);
//                Turno turnoObject = criaTurno(uc,turno, turma, inscritos);
//                criaAula(turnoObject, inscritos, diaDaSemana, horaInicio, horaFim, data, sala);
//
//            }
//            // debug logger
//            MemoryMXBean memBean = ManagementFactory.getMemoryMXBean();
//            MemoryUsage heapUsage = memBean.getHeapMemoryUsage();
//            ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
//            long cpuTime = threadMXBean.getCurrentThreadCpuTime() / 1_000_000; // convert to milliseconds
//            logger.debug("Memory usage: " + heapUsage.getUsed() / (1024 * 1024) + "MB");
//            logger.debug("CPU time: " + cpuTime + "ms");
//        } catch (org.json.simple.parser.ParseException | IOException ex) {
//            throw new RuntimeException(ex);
//        }
//        return horario;
//    }

}

