package com.anma.pdf;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ApachePoiServiceTest {

    private static final String PDF_PATH = "/home/andrii/files/LokiTime_Agreement_PDF.pdf";
    private static final String HTML_FILE = "";

    private final ApachePoiService apachePoiService = new ApachePoiServiceImpl();

    @BeforeEach
    void setUp() {
    }

    @Test
    void parsePDFTest() {

        try {

            apachePoiService.pdfToHTML(PDF_PATH, "./result.html");

        } catch (IOException | ParserConfigurationException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    void pdfImagesTest() {
        try {

            apachePoiService.generateImageFromPDF(PDF_PATH, "png");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}