package utils.uploader;

import com.google.gson.GsonBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.ICSVWriter;
import io.github.cdimascio.dotenv.Dotenv;
import models.*;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.ImportFileReader;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


import static org.apache.logging.log4j.core.util.FileUtils.getFileExtension;

public class FileUploader {
    
    private static final Logger logger = LoggerFactory.getLogger(FileUploader.class);
    private static Dotenv dotenv = Dotenv.load();
    private static String GITHUB_ACCESS_TOKEN = dotenv.get("GITHUB_ACCESS_TOKEN");

    private FileUploader() {
        throw new IllegalStateException("Classe de funções de utilidade!");
    }

    /**
     * Convert a Horario object to a String in CSV or JSON format
     *
     * @param horario the Horario object to be converted
     * @throws IllegalArgumentException if the specified format is not supported
     */
    public static String convertHorarioToFormat(Horario horario) {
        String content;
        if ("json".equalsIgnoreCase(getFileExtension(horario.getFile()))) {
            content = horarioToCsv(horario);
        } else if ("csv".equalsIgnoreCase(getFileExtension(horario.getFile()))) {
            content = horarioToJson(horario);
        } else {
            throw new IllegalArgumentException("Formato não suportado: " + getFileExtension(horario.getFile()));
        }
        return content;
    }

    public static String exportToGist(String fileName, String content) throws IOException{
        logger.info("Starting upload to GIST...");
        // check if the access token is set
        if (GITHUB_ACCESS_TOKEN == null) {
            JOptionPane.showMessageDialog(null, "Não foi configurado o access token do GitHub!",
                    "Error", JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("GITHUB_ACCESS_TOKEN environment variable not set");
        }
        return GistUploader.uploadToGist(fileName, content, GITHUB_ACCESS_TOKEN);
    }

    /**
     * Convert a Horario object to a String in CSV format
     *
     * @param horario the Horario object to be converted
     * @return the CSV data as a String
     */
    public static String horarioToCsv(Horario horario) {
        StringWriter stringWriter = new StringWriter();
        try (CSVWriter writer = new CSVWriter(stringWriter, ',',
                ICSVWriter.NO_QUOTE_CHARACTER,
                ICSVWriter.DEFAULT_ESCAPE_CHARACTER,
                ICSVWriter.DEFAULT_LINE_END)) {

            logger.info("Writing CSV file...");

            // adding header to csv
            String[] header = { "Curso" ,"Unidade Curricular","Turno","Turma","Inscritos no turno","Dia da semana",
                    "Hora início da aula","Hora fim da aula","Data da aula","Sala atribuída à aula","Lotação da sala"};
            writer.writeNext(header);

            // add data to csv
            for (UnidadeCurricular uc : horario.getUnidadesCurriculares()) {
                for (Aula aula : uc.getAulas()) {
                    SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
                    String dateString = outputFormat.format(aula.getDataAula().getData());
                    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                    String horaInicio = aula.getDataAula().getHoraInicio().format(timeFormatter);
                    String horaFim = aula.getDataAula().getHoraFim().format(timeFormatter);

                    String[] row = { uc.getCurso() , uc.getNomeUC(), aula.getTurno(), aula.getTurma() , aula.getNumInscritos().toString(),
                            aula.getDataAula().getDiaSemana().getName(), horaInicio, horaFim, dateString, aula.getSala(), aula.getLotacao().toString()} ;
                    writer.writeNext(row);
                }
            }
            logger.info("CSV file written successfully.");
        }
        catch (IOException e) {
            logger.error("Error writing CSV file: " + e.getMessage());
            e.printStackTrace();
        }
        return stringWriter.toString();
    }


    /**
     * Convert a Horario object to a String in JSON format
     *
     * @param horario the Horario object to be converted
     * @param outputFile the FileWriter object to write the converted Horario object

    public static void horarioToCsv(Horario horario, FileWriter outputFile) {

        try {

            // create CSVWriter object filewriter object as parameter
            CSVWriter writer = new CSVWriter(outputFile, ',',
                    ICSVWriter.NO_QUOTE_CHARACTER,
                    ICSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    ICSVWriter.DEFAULT_LINE_END);

            LOGGER.info("Writing CSV file...");

            // adding header to csv
            String[] header = { "Curso" ,"Unidade Curricular","Turno","Turma","Inscritos no turno","Dia da semana","Hora inÃ\u00ADcio da aula","Hora fim da aula","Data da aula","Sala atribuÃ\u00ADda Ã  aula","LotaÃ§Ã£o da sala"};
            writer.writeNext(header);

            // add data to csv
            for (UnidadeCurricular uc : horario.getUnidadesCurriculares()) {
                for (Aula aula : uc.getAulas()) {
                    SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
                    String dateString = outputFormat.format(aula.getDataAula().getData());
                    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                    String horaInicio = aula.getDataAula().getHoraInicio().format(timeFormatter);
                    String horaFim = aula.getDataAula().getHoraFim().format(timeFormatter);

                    String[] row = { uc.getCurso() , uc.getNomeUC(), aula.getTurno(), aula.getTurma() , aula.getNumInscritos().toString(),
                            aula.getDataAula().getDiaSemana().getName(), horaInicio, horaFim, dateString, aula.getSala(), aula.getLotacao().toString()} ;
                    writer.writeNext(row);
                }
            }
            // closing writer connection
            writer.close();
            LOGGER.info("CSV file written successfully.");
        }
        catch (IOException e) {
            LOGGER.severe("Error writing CSV file: " + e.getMessage());
            e.printStackTrace();

        }
    }*/

    /**
     * Convert a Horario object to a JSON formatted String
     *   the Horario object to be converted
     *  a JSON formatted String of the Horario object
     */

    /*public static void horarioToJson(Horario horario, FileWriter outputFile) throws IOException {
        Logger logger = Logger.getLogger("HorarioToJson");

        String[] header = { "Curso" ,"Unidade Curricular","Turno","Turma","Inscritos no turno","Dia da semana","Hora inÃ\u00ADcio da aula","Hora fim da aula","Data da aula","Sala atribuÃ\u00ADda Ã  aula","LotaÃ§Ã£o da sala"};

        List<Map<String, Object>> jsonData = new ArrayList<>();
        LOGGER.info("UCs: " + horario.getUnidadesCurriculares());
        for (UnidadeCurricular uc : horario.getUnidadesCurriculares()) {

            for (Aula aula : uc.getAulas()) {
                LOGGER.info("Aula: " + aula.getTurno());
                SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
                String dateString = outputFormat.format(aula.getDataAula().getData());
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                String horaInicio = aula.getDataAula().getHoraInicio().format(timeFormatter);
                String horaFim = aula.getDataAula().getHoraFim().format(timeFormatter);

                String[] row = {uc.getCurso(), uc.getNomeUC(), aula.getTurno(), aula.getTurma(), aula.getNumInscritos().toString(),
                        aula.getDataAula().getDiaSemana().getName(), horaInicio, horaFim, dateString, aula.getSala(), aula.getLotacao().toString()};

                Map<String, Object> jsonRow = createJsonDoc(header, row);
                LOGGER.info("\n\n JSON data: \n" + jsonRow);
                jsonData.add(jsonRow);

            }
        }
        LOGGER.info("Writing JSON file...");

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(jsonData);


        try {
            outputFile.write(json);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to write JSON to file", e);
            throw e;
        } finally {
            try {
                outputFile.close();
            } catch (IOException e) {
                logger.log(Level.WARNING, "Failed to close file", e);
            }
        }
    }*/


    /**
     * Convert a Horario object to a JSON formatted String
     *   the Horario object to be converted
     *  a JSON formatted String of the Horario object
     */

    public static String horarioToJson(Horario horario) {

        String[] header = { "Curso" ,"Unidade Curricular","Turno","Turma","Inscritos no turno","Dia da semana",
                "Hora início da aula","Hora fim da aula","Data da aula","Sala atribuída à aula","Lotação da sala"};
        List<Map<String, Object>> jsonData = new ArrayList<>();
        //LOGGER.info("UCs: " + horario.getUnidadesCurriculares());
        for (UnidadeCurricular uc : horario.getUnidadesCurriculares()) {

            for (Aula aula : uc.getAulas()) {

                SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
                String dateString = outputFormat.format(aula.getDataAula().getData());
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                String horaInicio = aula.getDataAula().getHoraInicio().format(timeFormatter);
                String horaFim = aula.getDataAula().getHoraFim().format(timeFormatter);

                String[] row = {uc.getCurso(), uc.getNomeUC(), aula.getTurno(), aula.getTurma(), aula.getNumInscritos().toString(),
                        aula.getDataAula().getDiaSemana().getName(), horaInicio, horaFim, dateString, aula.getSala(), aula.getLotacao().toString()};

                Map<String, Object> jsonRow = createJsonDoc(header, row);
                //LOGGER.info("\n\n JSON data: \n" + jsonRow);
                jsonData.add(jsonRow);

            }
        }
        logger.info("Writing JSON file...");

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(jsonData);
        logger.info("Created JSON String");
        return json;
/*
        try {
            outputFile.write(json);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to write JSON to file", e);
            throw e;
        } finally {
            try {
                outputFile.close();
            } catch (IOException e) {
                logger.log(Level.WARNING, "Failed to close file", e);
            }
        }*/
    }



    private static Map<String, Object> createJsonDoc(String[] headers, String[] row) {
        Map<String, Object> jsonDoc = new LinkedHashMap<>();
        for (int j = 0; j < row.length; j++) {
            try {
                // Procurar por valores do tipo integer
                int intValue = Integer.parseInt(row[j]);
                jsonDoc.put(headers[j], intValue);
            } catch (NumberFormatException e) {
                // Se não encontrar valores do tipo integer, regista como string
                jsonDoc.put(headers[j], row[j]);
            }
        }
        return jsonDoc;
    }

    public static void main(String[] args) {
        File fileFrom = new File("C:\\Users\\Public\\horarioMiniExemplo.json");

        Horario horario = new ImportFileReader().csvToHorario(fileFrom);
        horario.setFile(fileFrom);
        File fileTo = new File("C:\\Users\\Filipe\\Desktop\\horarioMiniExemplo.json");
        try (FileWriter writer = new FileWriter(fileTo)) {
            FileUploader.convertHorarioToFormat(horario);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}




