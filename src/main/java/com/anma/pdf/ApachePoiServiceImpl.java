package com.anma.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessReadBufferedFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;
import org.fit.pdfdom.PDFDomTree;

import javax.xml.parsers.ParserConfigurationException;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class ApachePoiServiceImpl implements ApachePoiService {

    @Override
    public void pdfToHTML(String filename, String to) throws IOException, ParserConfigurationException {
//        PDDocument pdf = PDDocument.load(new File(filename));
        try (PDDocument document = Loader.loadPDF(new RandomAccessReadBufferedFile(filename))) {
            Writer output = new PrintWriter(to, StandardCharsets.UTF_8);
            new PDFDomTree().writeText(document, output);
            output.close();
        }

    }

    @Override
    public void htmlToPDF(String filename, String to) throws IOException, DocumentException {
        Document document = new Document();

        PdfWriter writer = PdfWriter.getInstance(
                document, new FileOutputStream(to));
        document.open();
        XMLWorkerHelper.getInstance().parseXHtml(writer, document,
                new FileInputStream(filename));
        document.close();
    }

    @Override
    public void generateImageFromPDF(String filename, String extension) throws IOException {
//        PDDocument document = PDDocument.load(new File(filename));
        try (PDDocument document = Loader.loadPDF(new RandomAccessReadBufferedFile(filename))) {
            PDFRenderer pdfRenderer = new PDFRenderer(document);
            for (int page = 0; page < document.getNumberOfPages(); ++page) {
                BufferedImage bim = pdfRenderer.renderImageWithDPI(
                        page, 300, ImageType.RGB);
                ImageIOUtil.writeImage(
                        bim, String.format("src/main/resources/pdf-%d.%s", page + 1, extension), 300);
            }
        }

    }

    @Override
    public void generatePDFFromImage(String filename, String extension) throws IOException, DocumentException {
        Document document = new Document();
        String input = filename + "." + extension;
        String output = "src/output/" + extension + ".pdf";
        FileOutputStream fos = new FileOutputStream(output);

        PdfWriter writer = PdfWriter.getInstance(document, fos);
        writer.open();
        document.open();
        document.add(Image.getInstance((new URL(input))));
        document.close();
        writer.close();
    }

    @Override
    public void pdFtoText(String filename, String to) throws IOException {
        File file = new File(filename);
        String parsedText;

        PDFParser parser = new PDFParser(new RandomAccessReadBufferedFile(file));

        COSDocument cosDoc = parser.parse().getDocument();
        PDFTextStripper pdfStripper = new PDFTextStripper();
        PDDocument pdDoc = new PDDocument(cosDoc);
        parsedText = pdfStripper.getText(pdDoc);

        PrintWriter pw = new PrintWriter(to);
        pw.print(parsedText);
        pw.close();

    }
}
