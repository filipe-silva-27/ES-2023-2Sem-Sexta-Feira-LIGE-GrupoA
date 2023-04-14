package utils.saveCSVLocal;

public class MainTest {

    /**
     * The entry point for the CSV file downloader application.
     *
     * @param args the command line arguments (not used in this application)
     */
    public static void main(String[] args) {
        String fileUrl = "http://example.com/my_data.csv";
        String filePath = "";

        // Create a new instance of the CSVFileDownloader class
        CSVFileDownloader downloader = new CSVFileDownloader(fileUrl, filePath);

        // Set the file path based on the operating system
        if (downloader.getOsName().contains("win")) {
            downloader.setFilePath(System.getProperty("user.home") + "\\my_data.csv");
        }
        else if (downloader.getOsName().contains("mac")) {
            downloader.setFilePath(System.getProperty("user.home") + "/Documents/my_data.csv");
        }
        else {
            System.err.println("Unsupported operating system: " + downloader.getOsName());
            System.exit(1);
        }

        // Call the download method to download the CSV file
        downloader.download();
    }

   /* In this example, we create a new CSVFileDownloader object with the file URL and an empty file path.
   We then set the file path based on the operating system using the setFilePath() method and the getOsName()
   method to check the operating system name.

           Finally, we call the download() method on the downloader object to
    */
}

