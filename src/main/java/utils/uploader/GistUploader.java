package utils.uploader;

import org.eclipse.egit.github.core.*;
import org.eclipse.egit.github.core.service.GistService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;

/**
 * Classe utilitária para fazer upload de arquivos para o Gist do GitHub.
 */
public class GistUploader {

    private GistUploader() {}
    private static final String GIST_DESCRIPTION = "Gist created by my Java application";
    private static final boolean GIST_PUBLIC = true;
    private static final Logger LOGGER = LoggerFactory.getLogger(GistUploader.class);

    /**
     * Faz upload de um arquivo para o Gist do GitHub.
     *
     * @param fileName Nome do arquivo.
     * @param content Conteúdo do arquivo.
     * @param accessToken Token de acesso do GitHub.
     * @return URL do Gist criado.
     * @throws IOException Caso ocorra algum erro de I/O durante o upload.
     */
    public static String uploadToGist(String fileName, String content, String accessToken) throws IOException {

        LOGGER.info("Uploading file to Gist...");

        GistService gistService = new GistService();
        gistService.getClient().setOAuth2Token(accessToken);

        GistFile gistFile = new GistFile();
        gistFile.setContent(content);

        Map<String, GistFile> files = new HashMap<>();
        files.put(fileName, gistFile);

        Gist gist = new Gist();
        gist.setDescription(GIST_DESCRIPTION);
        gist.setPublic(GIST_PUBLIC);
        gist.setFiles(files);

        Gist newGist = gistService.createGist(gist);

        LOGGER.info("File uploaded to Gist with URL: {}", newGist.getHtmlUrl());
        return newGist.getHtmlUrl();
    }
}

