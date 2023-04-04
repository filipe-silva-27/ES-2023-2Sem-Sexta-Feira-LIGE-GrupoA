package utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;

public class CSVReader {
    public static void main(String[] args) {
        String pathToCsv = "PATH/TO/CSV";
        try (FileReader reader = new FileReader(pathToCsv);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader().withSkipHeaderRecord(false))) {
            for (CSVRecord record : csvParser) {
                // process each record here
                String curso = record.get(0);
                String unidadeCurricular = record.get(1);
                String turno = record.get(2);
                String turma = record.get(3);
                Integer inscritos = Integer.parseInt(record.get(4));
                String diaDaSemana = record.get(5);
                String horaInicio = record.get(6);
                String horaFim = record.get(7);
                String data = record.get(8);
                String sala = record.get(9);
                Integer lotacao = Integer.parseInt(record.get(10));
                // print out each value for the record
                System.out.println(curso + " | " + unidadeCurricular + " | " + turno + " | " + turma + " | " + inscritos
                        + " | " +
                        diaDaSemana + " | " + horaInicio + " | " + horaFim + " | " + data + " | " + sala + " | " + lotacao);

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.err.println("Invalid integer value in CSV file.");
            e.printStackTrace();
        }
    }
}
