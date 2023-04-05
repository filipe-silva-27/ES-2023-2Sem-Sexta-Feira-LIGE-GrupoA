package utils;

import com.opencsv.CSVReader;

import java.io.FileReader;

public class CsvReader {
    private static final String pathToCsv = "C:\\Users\\casa\\Desktop\\horario-exemplo.csv";
    public static void readDataLineByLine(String file) {

        try {

            // Create an object of filereader
            // class with CSV file as a parameter.
            FileReader filereader = new FileReader(file);

            // create csvReader object passing
            // file reader as a parameter
            CSVReader csvReader = new CSVReader(filereader);
            String[] nextRecord;

            // we are going to read data line by line
            while ((nextRecord = csvReader.readNext()) != null) {
                for (String cell : nextRecord) {
                    System.out.print(cell + "\t");
                }
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println("Read data line by line");
        readDataLineByLine(pathToCsv);

    }
}
