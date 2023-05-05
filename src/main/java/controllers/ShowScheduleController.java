package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import gui.App;
import models.Aula;
import models.UnidadeCurricular;
import org.apache.commons.lang3.SystemUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

/**
 * Esta classe é um controlador para exibir o horário de aulas.
 * Estende a classe ViewController.
 */
public class ShowScheduleController extends ViewController{

    private static final Logger logger = LoggerFactory.getLogger(ShowScheduleController.class);

    /**
     * Construtor da classe ShowScheduleController.
     * @param app A aplicação principal que será compartilhada por todos os controladores.
     */
    public ShowScheduleController(App app) {
        super(app);
        logger.info("- inicializado com sucesso.");
    }

    /**
     * Função que lê o ficheiro html template e gera um calendário dado um objeto JSON
     * @param aulas lista com todas as aulas para serem visualizadas
     */
    public static void createHtmlView(List<Aula> aulas) {
        // Load the template HTML file
        String templateContent = loadResourceFile("/calendar_template.html");
        if (templateContent == null) {
            JOptionPane.showMessageDialog(null, "Não foi encontrado o template do calendário",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Generate the JSON string and escape it
        String aulasJson = exportAulasToJson(aulas);
        String escapedAulasJson = StringEscapeUtils.escapeEcmaScript(aulasJson);

        // Create a temporary file to hold the HTML content
        Path tempFile;
        try {
            if (SystemUtils.IS_OS_UNIX) {
                FileAttribute<Set<PosixFilePermission>> attr =
                        PosixFilePermissions.asFileAttribute(PosixFilePermissions.fromString("rw-------"));
                tempFile = Files.createTempFile("calendar", ".html", attr);
            } else {
                tempFile = Files.createTempFile("calendar", ".html");
            }
            tempFile.toFile().deleteOnExit();

            // Generate the HTML content by replacing the JSON placeholder in the template
            String htmlContent = templateContent.replace("{{aulas_json}}", escapedAulasJson);
            Files.writeString(tempFile, htmlContent, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,
                    "Erro a criar o ficheiro HTML de visualização",
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        openBrowser(tempFile);
    }

    private static void openBrowser(Path path){
        // Open the generated HTML file in the default browser
        try {
            Desktop desktop = Desktop.getDesktop();
            if (desktop.isSupported(Desktop.Action.BROWSE)) {
                desktop.browse(path.toUri());
            } else {
                JOptionPane.showMessageDialog(null, "Erro a abrir o browser!",
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro a abrir o browser!",
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static String loadResourceFile(String fileName) {
        try (InputStream inputStream = ShowScheduleController.class.getResourceAsStream(fileName)) {
            if (inputStream == null) {
                return null;
            }
            return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException ex) {
            return null;
        }
    }


    /**
     * Função que gera a lista de Aulas mas em formato JSON
     * @param aulas
     * @return json String que corresponde ao JSON
     */
    public static String exportAulasToJson(List<Aula> aulas) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        objectMapper.setDateFormat(dateFormat);
        try {
            return objectMapper.writeValueAsString(aulas);
        } catch (JsonProcessingException e) {
            logger.error("Error creating JSON!", e);
            return "[]";
        }
    }


    /**
     * Obtém a lista de aulas do horário de aulas atual.
     * @return A lista de aulas ordenada.
     */
    public List<Aula> getAulas() {
        List<Aula> aulaList = new ArrayList<>();
        if (isHorarioSet()) {
            for(UnidadeCurricular uc : getHorario().getUnidadesCurriculares()){
                List<Aula> aulasAux = uc.getAulas();
                aulaList.addAll(aulasAux);
            }
        }
        Collections.sort(aulaList);
        logger.info("Aulas size: {}", aulaList.size());
        return aulaList;
    }

}
