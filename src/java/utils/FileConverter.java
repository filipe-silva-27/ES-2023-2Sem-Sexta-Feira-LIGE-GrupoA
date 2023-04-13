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

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.opencsv.CSVWriter;

import java.io.File;
import java.util.Iterator;

public class FileConverter{

    public static void convertCSVTOJSON(File csvFile, File jsonFile) {
        try {
            // Read CSV file
            CSVReader reader = new CSVReader(new FileReader(csvFile));
            List<String[]> data = reader.readAll();
            reader.close();

            // Convert CSV to List of Maps with Objects
            String[] headers = data.get(0);
            List<Map<String, Object>> jsonData = new ArrayList<>();

            for (int i = 1; i < data.size(); i++) {
                String[] row = data.get(i);
                Map<String, Object> jsonRow = new LinkedHashMap<>();
                for (int j = 0; j < row.length; j++) {
                    try {
                        // Try to parse the value as an integer
                        int intValue = Integer.parseInt(row[j]);
                        jsonRow.put(headers[j], intValue);
                    } catch (NumberFormatException e) {
                        // If it can't be parsed as an integer, add it as a string
                        jsonRow.put(headers[j], row[j]);
                    }
                }
                jsonData.add(jsonRow);
            }

            // Convert List of Maps with Objects to JSON
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(jsonData);

            // Write JSON to file
            FileWriter writer = new FileWriter(jsonFile);
            writer.write(json);
            writer.close();

            System.out.println("Conversion complete!");

        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
    }

    public static void convertJSONCSV(File jsonFile, File csvFile) throws IOException {
        // Read the JSON file into a JsonNode object
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(jsonFile);

        // Create the CSV writer
        CSVWriter csvWriter = new CSVWriter(new FileWriter(csvFile));

        // Create the CSV schema based on the JSON structure
        CsvSchema.Builder schemaBuilder = CsvSchema.builder();
        List<String> fieldNames = new ArrayList<>();
        Iterator<JsonNode> elements = rootNode.elements();
        if (elements.hasNext()) {
            JsonNode firstElement = elements.next();
            firstElement.fieldNames().forEachRemaining(fieldName -> {fieldNames.add(fieldName);
                schemaBuilder.addColumn(fieldName);});
        }
        CsvSchema csvSchema = schemaBuilder
                .setUseHeader(true)
                .setQuoteChar('\u0000')
                .build();

        // Write the CSV header and rows
        CsvMapper csvMapper = new CsvMapper();
        csvWriter.writeNext(fieldNames.toArray(new String[0]));
        while (elements.hasNext()) {
            JsonNode element = elements.next();
            List<String> rowValues = new ArrayList<>();
            fieldNames.forEach(fieldName -> {JsonNode fieldValue = element.get(fieldName);
                String fieldValueString = (fieldValue == null) ? "" : fieldValue.asText();
                rowValues.add(fieldValueString);});
            csvWriter.writeNext(rowValues.toArray(new String[0]));
        }

        // Close the CSV writer
        csvWriter.close();
    }

}