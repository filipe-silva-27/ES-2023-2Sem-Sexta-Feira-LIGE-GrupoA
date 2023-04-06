package utils;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SaveCSVLocal {

    public static void main(String[] args) {

        // Define the URL of the CSV file to download
        String fileUrl = "http://example.com/my_data.csv";

        // Define the path to the local file where the CSV file will be saved
        String filePath = "/path/to/my_data.csv";

        try {
            // Create a URL object for the file URL
            URL url = new URL(fileUrl);

            // Open a connection to the file URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Read the file contents from the server response
            InputStream inputStream = connection.getInputStream();
            byte[] buffer = new byte[1024];
            int bytesRead;
            FileOutputStream outputStream = new FileOutputStream(filePath);
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.close();
            inputStream.close();

            // Disconnect from the server
            connection.disconnect();

            System.out.println("CSV file downloaded and saved to " + filePath);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/*
In this program, we first define the URL of the CSV file that we want to download,
 and the path to the local file where we want to save it.


We then create a URL object for the file URL and open a connection to the server using the HttpURLConnection class. We set the request method to GET to download the file.

We then read the file contents from the server response and save them to the
local file using a FileOutputStream.
Finally, we disconnect from the server and print a message indicating
that the CSV file has been downloaded and saved to the local file.
 */





