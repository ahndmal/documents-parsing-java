package com.anma.pdf;

import com.anma.tika.pdf.TikaService;
import com.anma.tika.pdf.TikaServiceImpl;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

class TikaServiceTest {

    private static final String PDF_PATH = "/home/andrii/bht-pdf-files/files/LokiTime_Agreement_PDF.pdf";
    private static final String HTML_FILE = "./result.html";
    private static final String TEXT_FILE = "./result.txt";

    @Test
    void convertToText() {
        TikaService tikaService = new TikaServiceImpl();
        try {
            tikaService.convertToText(PDF_PATH);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    void convertToHTML() {
    }
}