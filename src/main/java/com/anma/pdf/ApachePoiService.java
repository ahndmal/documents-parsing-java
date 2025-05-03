package com.anma.pdf;

import com.itextpdf.text.DocumentException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public interface ApachePoiService {

    void pdfToHTML(String filename, String to) throws IOException, ParserConfigurationException;

    void htmlToPDF(String filename, String to) throws IOException, DocumentException;

    void generateImageFromPDF(String filename, String extension) throws IOException;

    void generatePDFFromImage(String filename, String extension) throws IOException, DocumentException;

    void pdFtoText(String filename, String to) throws IOException;

}
