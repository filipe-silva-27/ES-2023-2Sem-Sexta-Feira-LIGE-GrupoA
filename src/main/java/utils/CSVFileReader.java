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
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.lang.management.ThreadMXBean;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static java.lang.System.*;

public class CSVFileReader {

    private Horario horario = new Horario("");
    private static final Logger logger = LoggerFactory.getLogger(CSVFileReader.class);


    public Aula criaAula(Turno t, int lotacao, String diaDaSemana, String horaInicio, String horaFim, String data,
                         String sala) {
        Aula aula = null;
        //TODO Arranjar o formato das datas
        try{
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            Date dataObject = new SimpleDateFormat("dd/MM/yyyy").parse(data);
            DataAula dataAula = new DataAula(
                    DiaSemana.fromName(diaDaSemana),
                    LocalTime.parse(horaInicio, timeFormatter),
                    LocalTime.parse(horaFim, timeFormatter),
                    dataObject
            );
            Sala salaAula = new Sala(sala, lotacao);
            aula = new Aula(dataAula, salaAula);
            if(!t.addAula(aula))
                aula=t.getAulaBy(dataAula,salaAula);
        }catch (ParseException e) {
            logger.error("ParseException ocorreu devido à criação de DataAula");
        }
        return aula;
    }

    public UnidadeCurricular criaUC(String unidadeCurricular){
        UnidadeCurricular uc = new UnidadeCurricular(unidadeCurricular);
        if(!horario.addUnidadeCurricular(uc)) { //else precisamos de fazer addTurno e addCurso para um Objeto UC diferente da variavel uc
            uc = horario.getUnidadeCurricularPorNome(unidadeCurricular);
            if(uc == null){
                logger.error("Não foi encontrado a UnidadeCurricular com nome " + unidadeCurricular + " na lista de UCs");
                throw new IllegalStateException();
            }
        }
        return uc;
    }

    public void criaCursos(UnidadeCurricular uc, String curso){
        Set<Curso> listaCursos = new HashSet<>();
        String[] cursos = curso.split(",");
        for(String c : cursos){
            Curso cur = new Curso(c.trim());
            listaCursos.add(cur);
        }
        for (Curso c : listaCursos) {
            uc.addCurso(c);
        }
    }

    public Turno criaTurno(UnidadeCurricular uc, String idTurno, String turmas, Integer inscritos) {
        Turno t = new Turno(idTurno, turmas, inscritos);
        if (!uc.addTurno(t)){
            t=uc.getTurnoPorNome(idTurno);
        }
        return t;
    }


    public Horario CSVtoHorario (File fileCSV) {
        try (FileReader reader = new FileReader(fileCSV);
            CSVReader csvReader = new CSVReader(reader)){
            String[] headers = csvReader.readNext(); // ler o cabeçalhso
            String[] recrd;
            while ((recrd = csvReader.readNext()) != null) {

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

                UnidadeCurricular uc = criaUC(unidadeCurricular);
                criaCursos(uc, curso);
                Turno turnoObject = criaTurno(uc,turno, turma, inscritos);
                criaAula(turnoObject, inscritos, diaDaSemana, horaInicio, horaFim, data, sala);

            }
            // debug logger
            MemoryMXBean memBean = ManagementFactory.getMemoryMXBean();
            MemoryUsage heapUsage = memBean.getHeapMemoryUsage();
            ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
            long cpuTime = threadMXBean.getCurrentThreadCpuTime() / 1_000_000; // convert to milliseconds
            logger.debug("Memory usage: " + heapUsage.getUsed() / (1024 * 1024) + "MB");
            logger.debug("CPU time: " + cpuTime + "ms");
            logger.info("CSV LINES READ:" + String.valueOf(csvReader.getLinesRead()));
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            err.println("Invalid integer value in CSV file.");
            e.printStackTrace();
        }
        return horario;
    }

    public Horario JSONtoHorario(File jsonFile){
        try (FileReader reader = new FileReader(jsonFile)){
            Object o = new JSONParser().parse(reader);

            //debug
            o = new JSONParser().parse(new FileReader("C:\\Users\\Public\\horarioMiniExemplo.json"));

            JSONArray jArray = (JSONArray) o;

            for (Object doc: jArray) {

                JSONObject jsonDoc = (JSONObject) doc;

                String curso = (String) jsonDoc.get("﻿Curso");
                String unidadeCurricular = (String) jsonDoc.get("Unidade Curricular");
                String turno = (String) jsonDoc.get("Turno");
                String turma = (String) jsonDoc.get("Turma");
                Integer inscritos = ((Long) jsonDoc.get("Inscritos no turno")).intValue();
                String diaDaSemana = (String) jsonDoc.get("Dia da semana");
                String horaInicio = (String) jsonDoc.get("Hora inÃ\u00ADcio da aula");
                String horaFim = (String)  jsonDoc.get("Hora fim da aula");
                String data = (String) jsonDoc.get("Data da aula");
                String sala = (String) jsonDoc.get("Sala atribuÃ\u00ADda Ã  aula");
                Integer lotacao =((Long) jsonDoc.get("LotaÃ§Ã£o da sala")).intValue();


                logger.debug("Curso: " + curso);
                logger.debug("uc: " + unidadeCurricular);
                logger.debug("turno: " +turno);
                logger.debug("inscritos: " + inscritos);
                logger.debug("dia: " + diaDaSemana);
                logger.debug("horaIni: " + horaInicio);
                logger.debug("horaFim: " + horaFim);
                logger.debug("data: " + data);
                logger.debug("sala: " + sala);
                logger.debug("lotacao: " + lotacao);



                UnidadeCurricular uc = criaUC(unidadeCurricular);
                criaCursos(uc, curso);
                Turno turnoObject = criaTurno(uc,turno, turma, inscritos);
                criaAula(turnoObject, inscritos, diaDaSemana, horaInicio, horaFim, data, sala);

            }
            // debug logger
            MemoryMXBean memBean = ManagementFactory.getMemoryMXBean();
            MemoryUsage heapUsage = memBean.getHeapMemoryUsage();
            ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
            long cpuTime = threadMXBean.getCurrentThreadCpuTime() / 1_000_000; // convert to milliseconds
            logger.debug("Memory usage: " + heapUsage.getUsed() / (1024 * 1024) + "MB");
            logger.debug("CPU time: " + cpuTime + "ms");
        } catch (org.json.simple.parser.ParseException | IOException ex) {
            throw new RuntimeException(ex);
        }
        return horario;
    }


    /*public Horario CSVtoHorarioTeste() {
        // process each recrd here
//                String unidadeCurricular = recrd.get(1);
//                String curso = recrd.get(0);
//                String turno = recrd.get(2);
//                String turma = recrd.get(3);
//                String diaDaSemana = recrd.get(5);
//                String horaInicio = recrd.get(6);
//                String horaFim = recrd.get(7);
//                String data = recrd.get(8);
//                String sala = recrd.get(9);
//                Integer inscritos = Integer.parseInt(recrd.get(4));

        String unidadeCurricular = "Fundamentos de Arquitetura de Computadores";
        String curso = "LETI, LEI, LEI-PL, LIGE, LIGE-PL";
        String turno = "L0705TP23";
        String turma = "ET-A9, ET-A8, ET-A7, ET-A12, ET-A11, ET-A10";
        String diaDaSemana = "Sex";
        String horaInicio = "13:00:00";
        String horaFim = "14:30:00";
        String data = "09/12/2022";
        String sala = "C5.06";
        Integer inscritos = Integer.parseInt("70");

        UnidadeCurricular uc = criaUC(unidadeCurricular);
        criaCursos(uc, curso);
        Turno turnoObject = criaTurno(uc,turno, turma, inscritos);
        criaAula(turnoObject, inscritos, diaDaSemana, horaInicio, horaFim, data, sala);
        return horario;
    }*/
}

