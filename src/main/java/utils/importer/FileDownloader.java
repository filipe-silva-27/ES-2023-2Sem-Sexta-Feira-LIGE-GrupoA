package utils.importer;

import models.CustomExceptions;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * Classe de funções de utilidade para ir buscar ficheiros JSON ou CSV de um URL remoto
 */
public class FileDownloader {

    private FileDownloader(){
        throw new IllegalStateException("Classe de funções de utilidade!");
    }

    /**
     * Faz download de um ficheiro CSV ou JSON dado um link URL e guarda num ficheiro local
     *
     * @param remoteUrl o URL do ficheiro remoto
     * @return o ficheiro local caso seja feito com sucesso, null se não tiver sucesso
     * @throws IOException caso ocorrra um erro durante o processo de download ou de guardar o ficheiro
     * @throws models.CustomExceptions.InvalidFilenameException caso o utilizador escolha um nome de ficheiro inválido
     * @throws models.CustomExceptions.InvalidFileExtensionException caso a extensao do ficheiro seja inválida
     */
    public static File downloadFile(URL remoteUrl) throws IOException,
            CustomExceptions.InvalidFilenameException, CustomExceptions.InvalidFileExtensionException {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int result = fileChooser.showSaveDialog(null);
        if (result != JFileChooser.APPROVE_OPTION) {
            return null; // utilizador cancela o processo
        }

        File selectedDirectory = fileChooser.getSelectedFile();
        String fileName = JOptionPane.showInputDialog(null,
                "Introduza o nome do ficheiro (sem a extensão):");
        if (fileName == null || fileName.isEmpty()) {
            throw new CustomExceptions.InvalidFilenameException("Por favor escolha um nome válido!");
        }

        // Adiciona a extensão apropriada ao nome do arquivo
        String fileExtension = getFileExtension(remoteUrl);
        fileName += "." + fileExtension;
        File localFile = new File(selectedDirectory, fileName);

        if(fileExtension == null){
            throw new CustomExceptions.InvalidFileExtensionException("A extensão do ficheiro não é reconhecida!");
        }

        FileUtils.copyURLToFile(remoteUrl, localFile);
        if (!fileExtension.equalsIgnoreCase("csv") &&
                !fileExtension.equalsIgnoreCase("json")) {
            throw new CustomExceptions.InvalidFileExtensionException("O arquivo selecionado não é um arquivo CSV ou JSON.");
        }

        return localFile;
    }

    /**
     * Pede ao utilizador para inserir um URL remoto e faz download do ficheiro correspondente.
     *
     * @return o ficheiro local onde o ficheiro remoto foi salvo, ou null se o utilizador cancelar ou ocorrer erro.
     * @throws CustomExceptions.EmptyUrlException url do user é vazio
     * @throws IOException erro no download do ficheiro
     * @throws models.CustomExceptions.InvalidFilenameException caso o utilizador escolha um nome inválido para o ficheiro
     * @throws models.CustomExceptions.InvalidFileExtensionException caso a extensao do ficheiro seja inválida
     */
    public static File downloadRemoteFile() throws CustomExceptions.EmptyUrlException,
            IOException, CustomExceptions.InvalidFilenameException, CustomExceptions.InvalidFileExtensionException {

        String url = JOptionPane.showInputDialog(null, "Introduza o URL do ficheiro remoto:");
        if (url == null || url.isEmpty()) {
            throw new CustomExceptions.EmptyUrlException("Por favor introduza um URL válido.");
        }
        return downloadFile(new URL(url));

    }

    /**
     * Retorna a extensão de um arquivo.
     *
     * @param file o ficheiro para obter a extensão do ficheiro
     * @return a extensão do arquivo, ou null se o arquivo não tiver extensão
     */
    private static String getFileExtension(URL file) {
        String name = file.getPath();
        int dotIndex = name.lastIndexOf(".");
        return (dotIndex == -1) ? null : name.substring(dotIndex + 1);
    }

}
