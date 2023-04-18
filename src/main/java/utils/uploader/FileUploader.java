package utils.uploader;

import models.*;
import org.apache.commons.io.FileUtils;
import com.google.gson.Gson;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
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
     * @param format the desired output format, either "csv" or "json"
     * @return a String containing the converted Horario object in the specified format
     * @throws IllegalArgumentException if the specified format is not supported
     */
    public static String convertHorarioToFormat(Horario horario, String format) {
        if ("csv".equalsIgnoreCase(format)) {
            return horarioToCsv(horario);
        } else if ("json".equalsIgnoreCase(format)) {
            return horarioToJson(horario);
        } else {
            throw new IllegalArgumentException("Formato não suportado: " + format);
        }
    }

    /**
     * Convert a Horario object to a CSV formatted String
     *
     * @param horario the Horario object to be converted
     * @return a CSV formatted String of the Horario object
     */
    private static String horarioToCsv(Horario horario) {
        StringBuilder sb = new StringBuilder();
        // Add header
        sb.append("id,startTime,endTime,day,sala,lotacao,uc\n");
        // Add data
        for (UnidadeCurricular uc : horario.getUnidadesCurriculares()) {
            for (Turno turno : uc.getTurnos()) {
                for (Aula aula : turno.getAulas()) {
                    sb.append(aula.getDataAula().getDiaSemana().getAbbr()).append(",");
                    sb.append(aula.getDataAula().getHoraInicio()).append(",");
                    sb.append(aula.getDataAula().getHoraFim()).append(",");
                    sb.append(aula.getDataAula().getDiaSemana().getName()).append(",");
                    sb.append(aula.getSala()).append(",");
                    sb.append(aula.getLotacao()).append(",");
                    sb.append(uc.getNome()).append("\n");
                }
            }
        }
        return sb.toString();
    }


    /**
     * Convert a Horario object to a JSON formatted String
     *
     * @param horario the Horario object to be converted
     * @return a JSON formatted String of the Horario object
     */
    private static String horarioToJson(Horario horario) {
        Gson gson = new Gson();
        return gson.toJson(horario);
    }

    public static void main(String[] args) {
        File localFile = new File("path/to/file.csv");
        String remoteUrl = "https://example.com/upload";
        try {
            FileUploader.uploadFile(localFile, remoteUrl);
            System.out.println("Upload successful!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}




