package com.anma.pdf.pdfbox;

import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class PdfBoxServiceTest {

    private static final String PDF_PATH = "/home/andrii/bht-pdf-files/files/Raine and Horne Business Sales - Office365 - 2025-01-22 03.57.pdf";

    @Test
    void parseLoader() {
        try {
            PdfBoxService.parseLoader(PDF_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    void parse2DomTest() {
        Document domWithPdf2dom = PdfBoxService.getDomWithPdf2dom(PDF_PATH);
        System.out.println(domWithPdf2dom);
    }

    @Test
    void parseV2() {
    }

    @Test
    void testParseV2() {
    }
}