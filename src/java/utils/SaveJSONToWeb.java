package utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class SaveJSONToWeb {

    public static void main(String[] args) {

        // Define the URL of the web server where the JSON file will be uploaded
        String uploadUrl = "http://example.com/upload";

        // Define the path to the local JSON file to upload
        String filePath = "/path/to/my_data.json";

        try {
            // Create a URL object for the upload URL
            URL url = new URL(uploadUrl);

            // Open a connection to the upload URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setDoInput(true);

            // Create a DataOutputStream for writing the JSON file contents to the server
            DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());

            // Read the JSON file contents from the local file
            FileInputStream inputStream = new FileInputStream(new File(filePath));
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            inputStream.close();
            outputStream.flush();
            outputStream.close();

            // Get the server response
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Disconnect from the server
            connection.disconnect();

            System.out.println("JSON file uploaded to " + uploadUrl + ", server response: " + response.toString());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}


/*

In this program, we first define the URL of the web server where we want to upload the JSON file,
and the path to the local file that we want to upload.


We then create a URL object for the upload URL and open a connection to the server using the HttpURLConnection class.
        We set the request method to POST to upload the file.

We then create a DataOutputStream for writing the file contents to the server,
        and read the file contents from the local file using a FileInputStream.

We then write the file contents to the server using the DataOutputStream,
        and get the server response using a BufferedReader. Finally, we disconnect from the server and print a message indicating that the JSON file has been uploaded and the server response.

*/


