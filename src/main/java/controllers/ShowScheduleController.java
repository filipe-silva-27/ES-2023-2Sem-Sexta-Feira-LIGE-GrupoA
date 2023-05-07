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
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
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
     */
    public static void createHtmlView(List<Aula> aulas){
        String aulasJson = exportAulasToJson(aulas);
        String escapedAulasJson = StringEscapeUtils.escapeEcmaScript(aulasJson);


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

    /**
     * Função que abre um ficheiro dado uma path no browser
     * @param path path para o ficheiro a ser aberto
     * @see Desktop
     */
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

    /**
     * Devolve uma lista de todas as Aulas. Verifica se numInscritos é maior que lotacao para cada Aula e se for adiciona
     * a Aula a um novo array chamado aulasSobreLotadas.
     *
     * @return uma lista de Aulas que estão com excesso de alunos
     */
    public List<Aula> showAulasSobreLotadas() {
        List<Aula> aulaList = getAulas();
        List<Aula> aulasSobreLotadas = new ArrayList<>();
        // Iterar através de cada Aula e adicioná-la a aulasSobreLotadas se estiver com excesso de lotação
        for (Aula aula : aulaList) {
            // Ignorar salas com lotação -1 porque -1 quer dizer que o CSV nao tinha essa informação
            if (aula.getNumInscritos() > aula.getLotacao() && aula.getLotacao() != -1) {
                aulasSobreLotadas.add(aula);
            }
        }

        return aulasSobreLotadas;
    }


    //so testar: quando aulas sobrepostas sao null, quand ficam vazias, quando tem aulas sobrepostas, e quando aulas é null?
    public List<Aula> showSobreposicoes() {
        List<Aula> aulas = getAulas();
        List<Aula> aulasSobrepostas = new ArrayList<>();

        aulasSobrepostas = new ArrayList<>();
        for (int i = 0; i < aulas.size() -1; i++){
            for (int j = i+1; j < aulas.size(); j++){
                if (aulas.get(i).compareTo(aulas.get(j)) == 0) {
//                        logger.info("A1: {} | A2: {}", aulas.get(i).getDataAula().toString(),
//                                aulas.get(j).getDataAula().toString());
                    aulasSobrepostas.add(aulas.get(i));
                    aulasSobrepostas.add(aulas.get(j));
                }
            }
        }
        return aulasSobrepostas;

    }
}
