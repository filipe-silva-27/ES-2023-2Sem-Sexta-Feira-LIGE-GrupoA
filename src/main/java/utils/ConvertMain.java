package utils;

import java.io.File;
import java.io.IOException;

public class ConvertMain {

    public static void main(String[] args) {
        File output = new File( "C:\\Users\\Public\\horarioexemplo.json");
        FileConverter.convertCSVTOJSON(new File("C:\\Users\\Public\\horarioexemplo.csv"), output);
        try {
            FileConverter.convertJSONTOCSV(output, new File("C:\\Users\\Public\\horarioMiniExemplo2.csv"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
