package utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

public class SaveJSONLocal_win_mac {

    public static void main(String[] args) {

        // Define the URL of the JSON file to download
        String fileUrl = "http://example.com/my_data.json";

        // Define the path to the local file where the JSON file will be saved
        String filePath = "";

        // Get the operating system name
        String osName = System.getProperty("os.name").toLowerCase();

        // Set the file path based on the operating system
        if (osName.contains("win")) {
            // If the operating system is Windows, use the user's home directory as the base path
            filePath = System.getProperty("user.home") + "\\my_data.json";
        }
        else if (osName.contains("mac")) {
            // If the operating system is macOS, use the user's Documents directory as the base path
            filePath = System.getProperty("user.home") + "/Documents/my_data.json";
        }
        else {
            // If the operating system is not recognized, print an error message and exit
            System.err.println("Unsupported operating system: " + osName);
            System.exit(1);
        }

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

            System.out.println("JSON file downloaded and saved to " + filePath);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    In this program, we first define the URL of the JSON file that we want to download, and set the filePath variable to an empty string.

We then get the name of the operating system using the System.getProperty("os.name") method and convert it to lowercase.
 We use this to set the filePath variable based on the operating system. If the operating system is Windows, we use the user's home directory as the base path and append the file name. If the operating system is macOS, we use the user's Documents directory as the base path and append the file name. If the operating system is not recognized, we print an error message and exit.

We then create a URL object for the file URL and open a connection to the server using the openStream() method.
We save the file contents to the local file using a FileOutputStream.

Finally, we print a message indicating that the JSON file has been downloaded and saved to the local file.
     */

}
