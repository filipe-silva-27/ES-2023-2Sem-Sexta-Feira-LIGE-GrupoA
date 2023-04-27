package utils.exporter;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.importer.FileDownloader;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

class FileDownloaderTest {


    private FileDownloader fileDownloader;

    @BeforeEach
    void setUp() {
        fileDownloader = mock(FileDownloader.class);

    }

}