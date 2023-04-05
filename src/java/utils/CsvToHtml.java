package utils;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvToHtml {
    public static void main(String[] args) throws Exception {
        String csvFilePath = "C:\\Users\\casa\\Desktop\\horario-exemplo.csv";
        String htmlFilePath = "C:\\Users\\casa\\Desktop\\table.html";
        List<String[]> data = readCsv(csvFilePath);
        String html = generateHtmlTable(data);
        writeHtml(html, htmlFilePath);
    }

    private static List<String[]> readCsv(String csvFilePath) throws IOException, CsvException {
        CSVReader reader = new CSVReader(new FileReader(csvFilePath));
        return reader.readAll();
    }

    private static String generateHtmlTable(List<String[]> data) {
        StringBuilder sb = new StringBuilder();
        sb.append("<table>");
        for (String[] row : data) {
            sb.append("<tr>");
            for (String cell : row) {
                sb.append("<td>").append(cell).append("</td>");
            }
            sb.append("</tr>");
        }
        sb.append("</table>");
        return sb.toString();
    }

    private static void writeHtml(String html, String htmlFilePath) throws IOException {
        FileWriter writer = new FileWriter(htmlFilePath);
        writer.write(html);
        writer.close();
    }
}
