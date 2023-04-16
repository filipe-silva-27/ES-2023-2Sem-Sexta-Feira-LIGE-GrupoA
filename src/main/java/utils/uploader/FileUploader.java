package utils.uploader;

import org.apache.commons.io.FileUtils;

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

        LOGGER.info("Iniciando upload do arquivo " + file.getName() + " para " + remoteUrl);

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
