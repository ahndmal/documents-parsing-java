package com.anma.pdf.pdfbox;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class PdfBoxServiceTest {

    @Test
    void parseLoader() {
        try {
            PdfBoxService.parseLoader("/home/andrii/bht-pdf-files/files/141150182620.pdf");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void parseV2() {
    }

    @Test
    void testParseV2() {
    }
}