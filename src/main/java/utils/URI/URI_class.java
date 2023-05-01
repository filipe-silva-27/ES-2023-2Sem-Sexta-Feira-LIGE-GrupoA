package utils.URI;
import models.Horario;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class URI_class {



    /**
     * Creates a new Horario object with the given name and downloads the webcal file
     * from the URI.
     *
     * @param name the name of the horario
     * @param webcalUri the URI of the webcal file
     * @return the newly created Horario object
     * @throws IOException if there is an error downloading the webcal file
     * @throws URISyntaxException if the webcal URI is not valid
     */
    public static Horario create(String name, String webcalUri) throws IOException, URISyntaxException {
        // Download webcal file from the URI and store it in a temporary file
        Path tempFile = Files.createTempFile("horario", ".ics");
        Files.write(tempFile, URI.create(webcalUri).toURL().openStream().readAllBytes(),
                StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);

        // Create a new Horario object and add the downloaded webcal file to it
        Horario horario = new Horario(name);
        horario.setFile(tempFile.toFile());

        // TODO: Parse the webcal file and extract the schedule information
        // ...

        return horario;
    }


    /**
     The Main class allows users to download and parse an iCalendar file from a webcal URI, and create a Horario object that
     represents the schedule information contained in the iCalendar file.
     */

    /**
     * The main method allows users to input the name and webcal URI of a schedule file, and then creates a Horario object
     * to represent the schedule information in the file.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the name of the horario:");
        String name = scanner.nextLine();

        System.out.println("Enter the URI of the webcal:");
        String webcalUri = scanner.nextLine();

        try {
            // Download webcal file from the URI and store it in a temporary file
            Path tempFile = Files.createTempFile("horario", ".ics");
           // Files.write(tempFile, URI_class.create(name, webcalUri).toURL().openStream().readAllBytes(),
          //          StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);

            // Create a new Horario object and add the downloaded webcal file to it
            Horario horario = new Horario(name);
            horario.setFile(tempFile.toFile());

            // TODO: Parse the webcal file and extract the schedule information
            // ...

            // Print the Horario object
            System.out.println(horario);

        } catch (IOException e) { // | URISyntaxException
            System.err.println("Error downloading webcal file: " + e.getMessage());
            e.printStackTrace();
        }
    }



}
