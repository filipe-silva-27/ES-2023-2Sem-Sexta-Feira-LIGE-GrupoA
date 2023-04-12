package utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

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
     */
    public static File downloadFile(URL remoteUrl) throws IOException {
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
            JOptionPane.showMessageDialog(null, "Por favor escolha um nome válido!",
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        // Adiciona a extensão apropriada ao nome do arquivo
        String fileExtension = getFileExtension(remoteUrl);
        fileName += "." + fileExtension;
        File localFile = new File(selectedDirectory, fileName);

        FileUtils.copyURLToFile(remoteUrl, localFile);
        if (!fileExtension.equalsIgnoreCase("csv") &&
                !fileExtension.equalsIgnoreCase("json")) {

            JOptionPane.showMessageDialog(null,
                    "O arquivo selecionado não é um arquivo CSV ou JSON.",
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return null;

        }

        return localFile;
    }

    /**
     * Pede ao utilizador para inserir um URL remoto e faz download do ficheiro correspondente.
     *
     * @return o ficheiro local onde o ficheiro remoto foi salvo, ou null se o utilizador cancelar ou ocorrer erro.
     */
    public static File downloadRemoteFile(String url) {
        try {
            return downloadFile(new URL(url));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao fazer download do ficheiro.",
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
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
