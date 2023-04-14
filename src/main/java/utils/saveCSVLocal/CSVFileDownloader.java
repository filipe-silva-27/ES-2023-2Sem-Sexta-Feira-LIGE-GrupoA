package utils.saveCSVLocal;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

/**
 * A utility class for downloading CSV files from a given URL and saving them to a local file.
 */

public class CSVFileDownloader {

    private String fileUrl;
    private String filePath;
    private String osName;

    /**
     * Creates a new instance of the CSVFileDownloader class.
     *
     * @param fileUrl  the URL of the CSV file to download
     * @param filePath the path to the local file where the CSV file will be saved
     */

    public CSVFileDownloader(String fileUrl, String filePath) {
        this.fileUrl = fileUrl;
        this.filePath = filePath;
        this.osName = System.getProperty("os.name").toLowerCase();
    }

    /**
     * Downloads the CSV file from the URL and saves it to the local file.
     */
    public void download() {
        try {
            // Create a URL object for the file URL
            URL url = new URL(fileUrl);

            // Open a connection to the file URL
            InputStream inputStream = url.openStream();

            // Save the file contents to the local file
            FileOutputStream outputStream = new FileOutputStream(new File(filePath));
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.close();
            inputStream.close();

            System.out.println("CSV file downloaded and saved to " + filePath);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getOsName() {
        return osName;
    }

    public void setOsName(String osName) {
        this.osName = osName;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /*
    In this example, we create a new CSVFileDownloader class with instance variables for the file URL,
     file path, and operating system name.
     We also define a constructor that takes in the file URL and file path as parameters and sets the operating system name based on the system property.

We define a download() method that performs the same actions as the previous program,
 downloading the CSV file from the given URL and saving it to the local file.

We also define getter and setter methods for each of the instance variables,
allowing the user to modify the object's properties as needed.
     */
}
