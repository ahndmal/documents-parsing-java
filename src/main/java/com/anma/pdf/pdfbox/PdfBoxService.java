package com.anma.pdf.pdfbox;

import io.github.se_be.pdf2dom.PDFDomTree;
import io.github.se_be.pdf2dom.PDFDomTreeConfig;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.io.RandomAccessReadBufferedFile;
import org.apache.pdfbox.pdfparser.PDFStreamParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
//import org.fit.pdfdom.PDFDomTree;
//import org.fit.pdfdom.PDFDomTreeConfig;
import org.w3c.dom.Document;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class PdfBoxService {

    public static void parseLoader(String file) throws IOException {
        StringBuilder data = new StringBuilder();
        try (PDDocument document = Loader.loadPDF(new RandomAccessReadBufferedFile(file))) {
            System.out.println(document.getNumberOfPages());
            for (PDPage page : document.getPages()) {
                var reader = new BufferedReader(new InputStreamReader(page.getContents()));
                reader.lines().forEach(line -> {
                    data.append(line);
                    data.append(System.lineSeparator());
                });
            }
            Files.write(Path.of("src/resources/pbox/res.txt"), data.toString().getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
        }
    }

    public static Document getDomWithPdf2dom(String pdfFile) {

        // load the PDF file using PDFBox
        try (PDDocument pdf = Loader.loadPDF(new RandomAccessReadBufferedFile(pdfFile))) {
        // PDDocument pdf = PDDocument.load(new java.io.File("file.pdf"));
//            PDFDomTree parser = new PDFDomTree();
            PDFDomTree parser2 = new PDFDomTree(PDFDomTreeConfig.createDefaultConfig());
            Document dom = parser2.createDOM(pdf);
            return dom;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void parseV2() throws IOException {

       /* PDDocument pdDocument = PDDocument.load(Files.newInputStream(Path.of("")));

        PDStream contents = new PDStream(pdDocument);
//        PDStream contents = pdDocument.getPage(0).getContents();

        PDFStreamParser parser = new PDFStreamParser(contents.getStream());

        parser.parse();

        List<Object> tokens = parser.getTokens();*/
    }

    public static void parseV2(String path) throws IOException {
        PDFStreamParser parser = new PDFStreamParser(new byte[]{});
        parser.parse();
//        List<Object> tokens = parser.getTokens();
    }
}
