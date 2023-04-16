package utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;

import static java.lang.System.*;

public class CSVReader {
    public static void main(String[] args) {
        String pathToCsv = "C:\\Users\\Public\\horarioexemplo.csv";
        try (FileReader reader = new FileReader(pathToCsv);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.builder().setHeader().setSkipHeaderRecord(false).build())) {
            for (CSVRecord recrd : csvParser) {
                // process each recrd here
                String curso = recrd.get(0);
                String unidadeCurricular = recrd.get(1);
                String turno = recrd.get(2);
                String turma = recrd.get(3);
                int inscritos = Integer.parseInt(recrd.get(4));
                String diaDaSemana = recrd.get(5);
                String horaInicio = recrd.get(6);
                String horaFim = recrd.get(7);
                String data = recrd.get(8);
                String sala = recrd.get(9);
                int lotacao = 0;
                lotacao = Integer.parseInt(recrd.get(10));

                // print out each value for the recrd
                out.println(curso + " | " + unidadeCurricular + " | " + turno + " | " + turma + " | " + inscritos
                        + " | " +
                        diaDaSemana + " | " + horaInicio + " | " + horaFim + " | " + data + " | " + sala + " | " + lotacao);

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            err.println("Invalid integer value in CSV file.");
            e.printStackTrace();
        }
    }
}
