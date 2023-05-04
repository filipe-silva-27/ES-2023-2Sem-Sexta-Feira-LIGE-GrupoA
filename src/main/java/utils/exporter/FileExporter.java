package utils.exporter;

import com.google.gson.GsonBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.ICSVWriter;
import io.github.cdimascio.dotenv.Dotenv;
import models.*;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


import static org.apache.logging.log4j.core.util.FileUtils.getFileExtension;

/**
 *  Classe que transforma um horário num ficheio CSV ou JSON, guardando-o
 */
public class FileExporter {
    
    private static final Logger logger = LoggerFactory.getLogger(FileExporter.class);
    private static Dotenv dotenv = Dotenv.load();
    private static String githubAccessToken = dotenv.get("GITHUB_ACCESS_TOKEN");

    private FileExporter() {
        throw new IllegalStateException("Classe de funções de utilidade!");
    }

    /**
     * Convert a Horario object to a String in CSV or JSON format
     *
     * @param horario the Horario object to be converted
     * @throws IllegalArgumentException if the specified format is not supported
     */
    public static String convertHorarioToFormat(Horario horario) {
        String content;
        if ("json".equalsIgnoreCase(getFileExtension(horario.getFile()))) {
            content = horarioToCsv(horario);
        } else if ("csv".equalsIgnoreCase(getFileExtension(horario.getFile()))) {
            content = horarioToJson(horario);
        } else {
            throw new IllegalArgumentException("Formato não suportado: " + getFileExtension(horario.getFile()));
        }
        return content;
    }

    /**
     * Exporta um arquivo para um Gist do GitHub.
     *
     * @param fileName  O nome do arquivo a ser enviado para o Gist.
     * @param content   O conteúdo do arquivo a ser enviado para o Gist.
     * @return O URL do Gist criado.
     * @throws IOException Se ocorrer um erro durante o processo de exportação para o Gist.
     */
    public static String exportToGist(String fileName, String content) throws IOException{
        logger.info("Starting upload to GIST...");
        // check if the access token is set
        if (githubAccessToken == null) {
            JOptionPane.showMessageDialog(null, "Não foi configurado o access token do GitHub!",
                    "Error", JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("GITHUB_ACCESS_TOKEN environment variable not set");
        }
        return GistExporter.uploadToGist(fileName, content, githubAccessToken);
    }

    /**
     * Convert a Horario object to a String in CSV format
     *
     * @param horario the Horario object to be converted
     * @return the CSV data as a String
     */
    public static String horarioToCsv(Horario horario) {
        StringWriter stringWriter = new StringWriter();
        try (CSVWriter writer = new CSVWriter(stringWriter, ';',
                ICSVWriter.NO_QUOTE_CHARACTER,
                ICSVWriter.DEFAULT_ESCAPE_CHARACTER,
                ICSVWriter.DEFAULT_LINE_END)) {

            logger.info("Writing CSV file...");

            // adding header to csv
            String[] header = { "Curso" ,"Unidade Curricular","Turno","Turma","Inscritos no turno","Dia da semana",
                    "Hora início da aula","Hora fim da aula","Data da aula","Sala atribuída à aula","Lotação da sala"};
            writer.writeNext(header);

            // add data to csv
            for (UnidadeCurricular uc : horario.getUnidadesCurriculares()) {
                for (Aula aula : uc.getAulas()) {
                    SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
                    String dateString = outputFormat.format(aula.getDataAula().getData());
                    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                    String horaInicio = aula.getDataAula().getHoraInicio().format(timeFormatter);
                    String horaFim = aula.getDataAula().getHoraFim().format(timeFormatter);

                    String[] row = { uc.getCurso() , uc.getNomeUC(), aula.getTurno(), aula.getTurma() , aula.getNumInscritos().toString(),
                            aula.getDataAula().getDiaSemana().getName(), horaInicio, horaFim, dateString, aula.getSala(), aula.getLotacao().toString()} ;
                    writer.writeNext(row);
                }
            }
            logger.info("CSV file written successfully.");
        }
        catch (IOException e) {
            logger.error("Error writing CSV file: {}", e.getMessage());
        }
        return stringWriter.toString();
    }


    /**
     * Converte um objeto do tipo Horario para uma representação em formato JSON.
     *
     * @param horario O objeto Horario a ser convertido em JSON.
     * @return A representação JSON do objeto Horario.
     */
    public static String horarioToJson(Horario horario) {

        String[] header = { "Curso" ,"Unidade Curricular","Turno","Turma","Inscritos no turno","Dia da semana",
                "Hora início da aula","Hora fim da aula","Data da aula","Sala atribuída à aula","Lotação da sala"};
        List<Map<String, Object>> jsonData = new ArrayList<>();
        for (UnidadeCurricular uc : horario.getUnidadesCurriculares()) {

            for (Aula aula : uc.getAulas()) {

                SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
                String dateString = outputFormat.format(aula.getDataAula().getData());
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                String horaInicio = aula.getDataAula().getHoraInicio().format(timeFormatter);
                String horaFim = aula.getDataAula().getHoraFim().format(timeFormatter);

                String[] row = {uc.getCurso(), uc.getNomeUC(), aula.getTurno(), aula.getTurma(), aula.getNumInscritos().toString(),
                        aula.getDataAula().getDiaSemana().getName(), horaInicio, horaFim, dateString, aula.getSala(), aula.getLotacao().toString()};

                Map<String, Object> jsonRow = createJsonDoc(header, row);
                jsonData.add(jsonRow);

            }
        }
        logger.info("Writing JSON file...");

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(jsonData);
        logger.info("Created JSON String");
        return json;
    }

    /**
     * Cria um documento JSON a partir dos headers e valores de uma linha de dados.
     *
     * @param headers Array de strings com os cabeçalhos das colunas.
     * @param row Array de strings com os valores da linha de dados.
     * @return Um mapa contendo o documento JSON com os cabeçalhos como chaves e os valores como valores.
     */
    private static Map<String, Object> createJsonDoc(String[] headers, String[] row) {
        Map<String, Object> jsonDoc = new LinkedHashMap<>();
        for (int j = 0; j < row.length; j++) {
            try {
                // Procurar por valores do tipo integer
                int intValue = Integer.parseInt(row[j]);
                jsonDoc.put(headers[j], intValue);
            } catch (NumberFormatException e) {
                // Se não encontrar valores do tipo integer, regista como string
                jsonDoc.put(headers[j], row[j]);
            }
        }
        return jsonDoc;
    }
}




