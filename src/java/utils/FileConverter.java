package utils;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

public class FileConverter{

    public static void main(String[] args) {

        String csvFilePath = "horarioexemplo.csv";
        String jsonFilePath = "horarioexemplo3.json";

        try {
            // Read CSV file
            CSVReader reader = new CSVReader(new FileReader(csvFilePath));
            List<String[]> data = reader.readAll();
            reader.close();

            // Convert CSV to List of Maps
            String[] headers = data.get(0);
            List<Map<String, String>> jsonData = new ArrayList<>();

            for (int i = 1; i < data.size(); i++) {
                String[] row = data.get(i);
                Map<String, String> jsonRow = new LinkedHashMap<>();
                for (int j = 0; j < row.length; j++) {
                    jsonRow.put(headers[j], row[j]);
                }
                jsonData.add(jsonRow);
            }

            // Convert List of Maps to JSON
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(jsonData);

            // Write JSON to file
            FileWriter writer = new FileWriter(jsonFilePath);
            writer.write(json);
            writer.close();

            System.out.println("Conversion complete!");

        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }

    }

}
