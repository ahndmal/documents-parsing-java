package com.anma.pdf;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ApachePoiServiceTest {

//    private static final String PDF_PATH = "/home/andrii/bht-pdf-files/files/LokiTime_Agreement_PDF.pdf";
    private static final String PDF_PATH = "/home/andrii/bht-pdf-files/files/invoicesample.pdf";

    private static final String HTML_FILE = "./src/resources/html/result.html";
    private static final String TEXT_FILE = "./src/resources/txt/result.txt";

    private final ApachePoiService apachePoiService = new ApachePoiServiceImpl();

    @BeforeEach
    void setUp() {
    }

    @Test
    void parsePDFToHTMLTest() {
        try {

            apachePoiService.pdfToHTML(PDF_PATH, HTML_FILE);

        } catch (IOException | ParserConfigurationException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    void parsePDFToTextTest() {
        try {

            apachePoiService.pdFtoText(PDF_PATH, TEXT_FILE);

        } catch (IOException e) {
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