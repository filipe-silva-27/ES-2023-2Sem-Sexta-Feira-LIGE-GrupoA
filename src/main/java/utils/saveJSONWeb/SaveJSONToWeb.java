package utils.saveJSONWeb;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * The SaveJSONToWeb class provides a method to upload a JSON file to a web server.
 */
public class SaveJSONToWeb {

        /**
         * Uploads a JSON file to the specified web server.
         *
         * @param uploadUrl the URL of the web server where the JSON file will be uploaded
         * @param filePath the path to the local JSON file to upload
         */
        public void uploadJSON(String uploadUrl, String filePath) {
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
