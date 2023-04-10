package utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class FileDownloader {
    /**
     * Downloads a file from a remote URL and saves it to a local file.
     *
     * @param remoteUrl the URL of the remote file to download
     * @return the local file that the remote file was saved to
     * @throws IOException if an error occurs during the download or saving process
     */
    public static File downloadFile(URL remoteUrl) throws IOException {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int result = fileChooser.showSaveDialog(null);
        if (result != JFileChooser.APPROVE_OPTION) {
            return null; // User cancelled the dialog
        }

        File selectedDirectory = fileChooser.getSelectedFile();
        String fileName = getFileName(remoteUrl);
        File localFile = new File(selectedDirectory, fileName);

        FileUtils.copyURLToFile(remoteUrl, localFile);
        String fileExtension = getFileExtension(localFile);
        if (!fileExtension.equalsIgnoreCase("csv") && !fileExtension.equalsIgnoreCase("json")) {
            JOptionPane.showMessageDialog(null, "The selected file is not a CSV or JSON file.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        return localFile;
    }


    /**
     * Prompts the user to enter a remote URL and downloads the corresponding file.
     *
     * @return the local file that the remote file was saved to, or null if the user cancelled the dialog
     */
    public static File downloadRemoteFile() {
        String url = JOptionPane.showInputDialog(null, "Enter the URL of the remote file:");
        if (url == null || url.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a valid URL.", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        try {
            return downloadFile(new URL(url));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "An error occurred while downloading the file.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    /**
     * Returns the file extension of a file.
     *
     * @param file the file to get the extension from
     * @return the extension of the file, or null if the file has no extension
     */
    private static String getFileExtension(File file) {
        String name = file.getName();
        int dotIndex = name.lastIndexOf(".");
        return (dotIndex == -1) ? null : name.substring(dotIndex + 1);
    }

    /**
     * Returns the file name of a URL.
     *
     * @param url the URL to get the file name from
     * @return the file name of the URL
     */
    private static String getFileName(URL url) {
        String path = url.getPath();
        int lastIndexOfSlash = path.lastIndexOf("/");
        return (lastIndexOfSlash == -1) ? path : path.substring(lastIndexOfSlash + 1);
    }

}
