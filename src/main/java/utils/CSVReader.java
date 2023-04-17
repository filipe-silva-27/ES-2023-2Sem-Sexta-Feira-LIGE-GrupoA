package utils;

import com.fasterxml.jackson.databind.util.StdDateFormat;
import models.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static java.lang.System.*;

public class CSVReader {

    private Horario horario = new Horario("Horario");
    private static final Logger logger = LoggerFactory.getLogger(CSVReader.class);

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

    public Horario CSVtoHorarioTeste() {
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
    }


    public Horario CSVtoHorario (File fileCSV) {
        String pathToCsv = "C:\\Users\\Public\\horarioexemplo.csv";
        try (FileReader reader = new FileReader(fileCSV);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.builder().setHeader().setSkipHeaderRecord(false).build())) {
            for (CSVRecord recrd : csvParser) {
                // process each recrd here

                String unidadeCurricular = recrd.get(1);
                String curso = recrd.get(0);
                String turno = recrd.get(2);
                String turma = recrd.get(3);
                String diaDaSemana = recrd.get(5);
                String horaInicio = recrd.get(6);
                String horaFim = recrd.get(7);
                String data = recrd.get(8);
                String sala = recrd.get(9);
                Integer inscritos = Integer.parseInt(recrd.get(4));

                UnidadeCurricular uc = criaUC(unidadeCurricular);
                criaCursos(uc, curso);
                Turno turnoObject = criaTurno(uc,turno, turma, inscritos);
                criaAula(turnoObject, inscritos, diaDaSemana, horaInicio, horaFim, data, sala);


            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            err.println("Invalid integer value in CSV file.");
            e.printStackTrace();
        }
        return horario;
    }

    public static void main(String[] args) {
        CSVReader csvReader = new CSVReader();
        Horario horario = csvReader.CSVtoHorarioTeste();
        logger.info(horario.toString());
    }

}

