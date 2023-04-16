package utils.saveJSONLocal;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

/**
 A utility class for saving a JSON file from a URL to the local file system.
 */
public class saveJSONToLocal {

        /**
         * Downloads a JSON file from a specified URL and saves it to the local file system.
         * <p>
         * The file path is determined based on the operating system.
         *
         * @param fileUrl the URL of the JSON file to download
         * @throws Exception if there is an error downloading or saving the file
         */
        public static void saveJSONToLocal(String fileUrl) throws Exception {

// Define the path to the local file where the JSON file will be saved
            String filePath = getLocalFilePath();

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

        /**
         * Gets the local file path where the JSON file will be saved based on the operating system.
         *
         * @return the file path for the local file
         * @throws Exception if the operating system is not recognized
         */
        private static String getLocalFilePath() throws Exception {

// Get the operating system name
            String osName = System.getProperty("os.name").toLowerCase();

// Set the file path based on the operating system
            if (osName.contains("win")) {
// If the operating system is Windows, use the user's home directory as the base path
                return System.getProperty("user.home") + "my_data.json";    //estava "\my_data.json"
            } else if (osName.contains("mac")) {
// If the operating system is macOS, use the user's Documents directory as the base path
                return System.getProperty("user.home") + "/Documents/my_data.json";
            } else {
// If the operating system is not recognized, throw an exception
                throw new Exception("Unsupported operating system: " + osName);
            }
        }
    }
/*

In this program, we first define the URL of the JSON file that we want to download and call the saveJSONToLocal method.
In the saveJSONToLocal method, we define the path to the local file where the JSON file will be saved by calling the getLocalFilePath method,
create a URL object for the file URL and open a connection to the server using the openStream() method,
and save the file contents to the local file using a FileOutputStream.
The getLocalFilePath method gets the name of the operating system using the System.getProperty("os.name") method and converts it to lowercase.
We use this to set the file path variable based on the operating system.
If the operating system is Windows, we use the user's home directory as the base path and append the file name.
If the operating system is macOS, we use the user's Documents directory as the base path and append the file name.
If the operating system is not recognized, we throw an exception.
Finally, we print a message indicating that the JSON file has been downloaded and saved to the local file.
*/


