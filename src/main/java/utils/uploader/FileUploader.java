package utils.uploader;

import com.google.gson.GsonBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import models.*;
import org.apache.commons.io.FileUtils;
import com.google.gson.Gson;
import utils.ImportFileReader;

import javax.swing.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileUploader {

    private static final Logger LOGGER = Logger.getLogger(FileUploader.class.getName());
    private FileUploader(){
        throw new IllegalStateException("Classe de funções de utilidade!");
    }

    /**
     * Faz upload de um ficheiro CSV ou JSON para um URL remoto
     *
     * @param file o ficheiro local a ser enviado
     * @param remoteUrl o URL remoto onde o ficheiro deve ser enviado
     * @throws IOException caso ocorra um erro durante o processo de upload ou de leitura do ficheiro
     */
    public static void uploadFile(File file, String remoteUrl) throws IOException {

        LOGGER.info("A iniciar upload do arquivo " + file.getName() + " para " + remoteUrl);

        String fileExtension = getFileExtension(file);
        if (!fileExtension.equalsIgnoreCase("csv") && !fileExtension.equalsIgnoreCase("json")) {
            String errorMsg = "O arquivo selecionado não é um arquivo CSV ou JSON.";
            LOGGER.warning(errorMsg);
            throw new IllegalArgumentException(errorMsg);
        }

        URL url = new URL(remoteUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "text/" + fileExtension);

        //byte[] fileBytes = FileUtils.readFileToByteArray(file);
        byte[] fileBytes = null;
        try {
            fileBytes = FileUtils.readFileToByteArray(file);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Erro ao ler o arquivo: " + e.getMessage(), e);
            throw new IOException("Erro ao ler o arquivo: " + e.getMessage(), e);
        }

        //conn.getOutputStream().write(fileBytes);
        try {
            conn.getOutputStream().write(fileBytes);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Erro ao enviar o arquivo: " + e.getMessage(), e);
            throw new IOException("Erro ao enviar o arquivo: " + e.getMessage(), e);
        }

        if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
            String errorMsg = "Falha ao enviar o arquivo: " + conn.getResponseCode() + " " + conn.getResponseMessage();
            LOGGER.warning(errorMsg);
            throw new RuntimeException(errorMsg);
        }

        LOGGER.info("Arquivo " + file.getName() + " enviado com sucesso para " + remoteUrl);
    }

    /**
     * Retorna a extensão de um arquivo.
     *
     * @param file o ficheiro para obter a extensão do ficheiro
     * @return a extensão do arquivo, ou null se o arquivo não tiver extensão
     */
    private static String getFileExtension(File file) {
        String name = file.getName();
        int dotIndex = name.lastIndexOf(".");
        return (dotIndex == -1) ? null : name.substring(dotIndex + 1);
    }



    /**
     * Convert a Horario object to a String in CSV or JSON format
     *
     * @param horario the Horario object to be converted
     * @param writer the FileWriter object to write the converted Horario object
     * @return a String containing the converted Horario object in the specified format
     * @throws IllegalArgumentException if the specified format is not supported
     */
    public static void convertHorarioToFormat(Horario horario, FileWriter writer) throws IOException {
        if ("csv".equalsIgnoreCase(getFileExtension(horario.getFile()))) {
            horarioToCsv(horario, writer);
        } else if ("json".equalsIgnoreCase(getFileExtension(horario.getFile()))) {
            horarioToJson(horario, writer);
        } else {
            throw new IllegalArgumentException("Formato não suportado: " + getFileExtension(horario.getFile()));
        }
    }

    /**
     * Convert a Horario object to a String in JSON format
     *
     * @param horario the Horario object to be converted
     * @param outputFile the FileWriter object to write the converted Horario object
     * @return a String containing the converted Horario object in the specified format
     */
    private static void horarioToCsv(Horario horario, FileWriter outputFile) {

        try {

            // create CSVWriter object filewriter object as parameter
            CSVWriter writer = new CSVWriter(outputFile, ',',
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);

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
    }


    /**
     * Convert a Horario object to a JSON formatted String
     *
     *   the Horario object to be converted
     *  a JSON formatted String of the Horario object
     */

    private static void horarioToJson(Horario horario, FileWriter outputFile) throws IOException {
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

    /*public static void main(String[] args) {
        File localFile = new File("path/to/file.csv");
        String remoteUrl = "https://example.com/upload";
        try {
            FileUploader.uploadFile(localFile, remoteUrl);
            System.out.println("Upload successful!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    public static void main(String[] args) {
            File fileFrom = new File("C:\\Users\\Public\\horarioMiniExemplo.json");

            Horario horario = new ImportFileReader().csvToHorario(fileFrom);
            horario.setFile(fileFrom);
            File fileTo = new File("C:\\Users\\Filipe\\Desktop\\horarioMiniExemplo.json");
            try (FileWriter writer = new FileWriter(fileTo)) {
                FileUploader.convertHorarioToFormat(horario, writer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        //}
    }
}




