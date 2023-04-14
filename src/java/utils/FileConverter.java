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
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.opencsv.CSVWriter;

import java.io.File;
import java.util.Iterator;

/**
 * Classe utilitaria com metodos para converter ficheiros CSV para JSON e JSON para CSV.
 */
public class FileConverter {

    /**
     * Converte ficheiro CSV para JSON.
     *
     * @param csvFile  ficheiro CSV a converter
     * @param jsonFile ficheiro JSON para onde sera convertido o CSV
     */
    public static void convertCSVTOJSON(File csvFile, File jsonFile) {
        try {
            // Ler ficheiro CSV
            CSVReader reader = new CSVReader(new FileReader(csvFile));
            List<String[]> data = reader.readAll();
            reader.close();

            // Converter CSV para uma lista de mapas com objetos
            String[] headers = data.get(0);
            List<Map<String, Object>> jsonData = new ArrayList<>();

            for (int i = 1; i < data.size(); i++) {
                String[] row = data.get(i);
                Map<String, Object> jsonRow = new LinkedHashMap<>();
                for (int j = 0; j < row.length; j++) {
                    try {
                        // Procurar por valores do tipo integer
                        int intValue = Integer.parseInt(row[j]);
                        jsonRow.put(headers[j], intValue);
                    } catch (NumberFormatException e) {
                        // Se não encontrar valores do tipo integer, regista como string
                        jsonRow.put(headers[j], row[j]);
                    }
                }
                jsonData.add(jsonRow);
            }

            // Converte a lista de mapas com objetos para JSON
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(jsonData);

            // Escrever ficheiro JSON
            FileWriter writer = new FileWriter(jsonFile);
            writer.write(json);
            writer.close();

        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
    }

    /**
     * Converte ficheiro JSON para CSV
     *
     * @param jsonFile ficheiro JSON a converter
     * @param csvFile  ficheiro de CSV para onde vai ser convertido o JSON
     * @throws IOException para o caso de existir algum erro na leitura ou escrita dos ficheiros
     */
    public static void convertJSONTOCSV(File jsonFile, File csvFile) throws IOException {
        //Leitura do ficheiro JSON para objeto JsonNode
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(jsonFile);

        // Criar escritor do ficheiro CSV
        CSVWriter csvWriter = new CSVWriter(new FileWriter(csvFile));

        //Criar esquema do CSV com base nos parametros presentes no ficheiro JSON
        CsvSchema.Builder schemaBuilder = CsvSchema.builder();
        List<String> fieldNames = new ArrayList<>();
        Iterator<JsonNode> elements = rootNode.elements();
        while (elements.hasNext()) {
            JsonNode element = elements.next();
            element.fieldNames().forEachRemaining(fieldName -> {
                if (!fieldNames.contains(fieldName)) {
                    fieldNames.add(fieldName);
                    schemaBuilder.addColumn(fieldName);
                }
            });
        }

        // Escreve o cabeçalho e colunas do ficheiro CSV
        csvWriter.writeNext(fieldNames.toArray(new String[0]));
        elements = rootNode.elements();
        while (elements.hasNext()) {
            JsonNode element = elements.next();
            List<String> rowValues = new ArrayList<>();
            fieldNames.forEach(fieldName -> {
                JsonNode fieldValue = element.get(fieldName);
                String fieldValueString = (fieldValue == null) ? "" : fieldValue.asText();
                rowValues.add(fieldValueString);
            });
            csvWriter.writeNext(rowValues.toArray(new String[0]));
        }

        // Fechar o escritor do ficheiro CSV
        csvWriter.close();
    }

}