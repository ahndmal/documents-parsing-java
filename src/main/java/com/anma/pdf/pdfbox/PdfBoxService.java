package com.anma.pdf.pdfbox;

import org.apache.pdfbox.pdfparser.PDFStreamParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.common.PDStream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class PdfBoxService {
    public static void parse () throws IOException {

        PDDocument pdDocument = PDDocument.load(Files.newInputStream(Path.of("")));

        PDStream contents = new PDStream(pdDocument);
//        PDStream contents = pdDocument.getPage(0).getContents();

        PDFStreamParser parser = new PDFStreamParser(contents.getStream());

        parser.parse();

        List<Object> tokens = parser.getTokens();
    }

    public static void parse (String path) throws IOException {
        PDFStreamParser parser = new PDFStreamParser(new byte[] {});
        parser.parse();
        List<Object> tokens = parser.getTokens();
    }
}
