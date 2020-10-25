package com.anma.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;
import org.fit.pdfdom.PDFDomTree;

import javax.xml.parsers.ParserConfigurationException;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class PDFMain {

    public static void main(String[] args) throws IOException, ParserConfigurationException {

        pdfToHTML("E:\\programming\\java\\projects\\documents-parsing\\src\\resources\\pdf\\Design_test_import.pdf");

    }

    private static void pdfToHTML(String filename) throws IOException, ParserConfigurationException {

        PDDocument pdf = PDDocument.load(new File(filename));
        Writer output = new PrintWriter("E:\\programming\\java\\projects\\documents-parsing\\src\\resources\\pdf\\out\\pdf_1.html", "utf-8");
        new PDFDomTree().writeText(pdf, output);

        output.close();
    }

    private static void htmlToPDF(String filename) throws IOException, DocumentException {

        Document document = new Document();

        PdfWriter writer = PdfWriter.getInstance(document,
                new FileOutputStream("src/output/html.pdf"));
        document.open();
        XMLWorkerHelper.getInstance().parseXHtml(writer, document,
                new FileInputStream(filename));
        document.close();
    }

    private static void generateImageFromPDF(String filename, String extension) throws IOException {
        PDDocument document = PDDocument.load(new File(filename));
        PDFRenderer pdfRenderer = new PDFRenderer(document);
        for (int page = 0; page < document.getNumberOfPages(); ++page) {
            BufferedImage bim = pdfRenderer.renderImageWithDPI(
                    page, 300, ImageType.RGB);
            ImageIOUtil.writeImage(
                    bim, String.format("src/output/pdf-%d.%s", page + 1, extension), 300);
        }
        document.close();
    }

    private static void generatePDFFromImage(String filename, String extension) throws IOException, DocumentException {
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


}
