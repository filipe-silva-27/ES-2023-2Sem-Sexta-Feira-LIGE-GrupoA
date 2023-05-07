package models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import models.CustomExceptions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomExceptionsTest {
    CustomExceptions.EmptyUrlException emptyUrlException;
    CustomExceptions.InvalidFilenameException invalidFilenameException;
    CustomExceptions.InvalidFileExtensionException invalidFileExtensionException;

    @BeforeEach
    void setUp() {
        emptyUrlException = new CustomExceptions.EmptyUrlException("URL is empty");
        invalidFilenameException = new CustomExceptions.InvalidFilenameException("Invalid filename");
        invalidFileExtensionException = new CustomExceptions.InvalidFileExtensionException("Invalid file extension");
    }

    @AfterEach
    void tearDown() {
        emptyUrlException = null;
        invalidFilenameException = null;
        invalidFileExtensionException = null;
    }
    

    @Test
    void testEmptyUrlException() {
        assertEquals("URL is empty", emptyUrlException.getMessage());
    }

    @Test
    void testInvalidFilenameException() {
        assertEquals("Invalid filename", invalidFilenameException.getMessage());
    }

    @Test
    void testInvalidFileExtensionException() {
        assertEquals("Invalid file extension", invalidFileExtensionException.getMessage());
    }
}

