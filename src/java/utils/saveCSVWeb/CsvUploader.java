package utils.saveCSVWeb;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

/**
 * A class for uploading a CSV file to a web server.
 */
public class CsvUploader {

    private final String serverUrl;
    private final String username;
    private final String password;
    private final String filePath;

    /**
     * Constructor for CsvUploader.
     *
     * @param serverUrl The URL of the web server where the CSV file will be uploaded
     * @param username  The username for authentication (if required)
     * @param password  The password for authentication (if required)
     * @param filePath  The path to the CSV file
     */
    public CsvUploader(String serverUrl, String username, String password, String filePath) {
        this.serverUrl = serverUrl;
        this.username = username;
        this.password = password;
        this.filePath = filePath;
    }

    /**
     * Uploads the CSV file to the web server.
     */
    public void upload() {
        try {
            // Read the contents of the CSV file
            File file = new File(filePath);
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] fileBytes = new byte[(int) file.length()];
            fileInputStream.read(fileBytes);
            fileInputStream.close();

            // Encode the CSV file contents as a Base64 string
            String fileData = Base64.getEncoder().encodeToString(fileBytes);

            // Create a URL object for the server URL
            URL url = new URL(serverUrl);

            // Open a connection to the server URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");

            // Set the request headers
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Content-Length", String.valueOf(fileData.length()));

            // Set the basic authentication credentials (if required)
            String credentials = username + ":" + password;
            String auth = "Basic " + Base64.getEncoder().encodeToString(credentials.getBytes());
            connection.setRequestProperty("Authorization", auth);

            // Enable output and send the file contents to the server
            connection.setDoOutput(true);
            connection.getOutputStream().write(fileData.getBytes());

            // Read the server response
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String response;
            while ((response = in.readLine()) != null) {
                System.out.println(response);
            }
            in.close();

            // Disconnect from the server
            connection.disconnect();

            System.out.println("CSV file uploaded to the server.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //TESTE
  //  To use this class, you can create an instance of CsvUploader with the
    //  necessary parameters, and then call the upload() method. For example:

    //CsvUploader uploader = new CsvUploader("http://example.com/upload", "myusername", "mypassword", "/path/to/my_data.csv");
//uploader.upload();

}


    /*In this program, we first define the URL of the web server where the CSV file will be uploaded, and the username and password for authentication (if required).

    We then define the path to the existing CSV file that we want to upload.

            Next, we read the contents of the CSV file and encode them as a Base64 string.

    We then create a URL object for the server URL and open a connection to the server using the HttpURLConnection class.
    We set the request method to POST and add the necessary request headers, including the basic authentication credentials (if required).
    We also enable output and send the file contents to the server.

    Finally, we read the server response, disconnect from the server, and print a message indicating that the CSV file has been uploaded to the server.
*/


