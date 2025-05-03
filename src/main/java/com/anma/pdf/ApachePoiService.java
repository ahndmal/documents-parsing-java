package com.anma.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessFile;
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

public interface ApachePoiService {

    void pdfToHTML(String filename, String to) throws IOException, ParserConfigurationException;

    void htmlToPDF(String filename, String to) throws IOException, DocumentException;

    void generateImageFromPDF(String filename, String extension) throws IOException;

    void generatePDFFromImage(String filename, String extension) throws IOException, DocumentException;

    void pdFtoText(String filename, String to) throws IOException;

}
