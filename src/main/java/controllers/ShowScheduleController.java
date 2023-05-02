package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import gui.App;
import models.Aula;
import models.UnidadeCurricular;
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
import java.nio.file.StandardCopyOption;
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
     */
    public static void createHtmlView(List<Aula> aulas){
        String aulasJson = exportAulasToJson(aulas);
        String escapedAulasJson = StringEscapeUtils.escapeEcmaScript(aulasJson);

        // Ler o template HTML
        InputStream templateStream = ShowScheduleController.class.getResourceAsStream("/calendar_template.html");
        if (templateStream != null) {
            try {
                Path tempFile = Files.createTempFile("calendar", ".html");
                Files.copy(templateStream, tempFile, StandardCopyOption.REPLACE_EXISTING);
                String htmlTemplate = Files.readString(tempFile, StandardCharsets.UTF_8);
                // Carregar o JSON para o HTML
                String htmlOutput = htmlTemplate.replace("{{aulas_json}}", escapedAulasJson);

                Files.writeString(tempFile, htmlOutput, StandardCharsets.UTF_8);

                if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                    Desktop.getDesktop().browse(tempFile.toUri());
                } else {
                    JOptionPane.showMessageDialog(null, "Erro a abrir o browser!",
                            "Erro", JOptionPane.ERROR_MESSAGE);
                }

            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null,
                        "Erro a criar o ficheiro HTML de visualização",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Não foi encontrado o template do calendário",
                    "Error", JOptionPane.ERROR_MESSAGE);
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
