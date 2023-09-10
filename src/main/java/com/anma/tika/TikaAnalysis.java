package com.anma.tika;

import java.io.*;

import org.apache.tika.Tika;
import org.apache.tika.detect.DefaultDetector;
import org.apache.tika.detect.Detector;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.mime.MediaType;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.apache.tika.sax.ToXMLContentHandler;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

public class TikaAnalysis {

//    public String parseToStringExample() throws IOException, SAXException, TikaException {
//        Tika tika = new Tika();
//        try (InputStream stream = ParsingExample.class.getResourceAsStream("test.doc")) {
//            return tika.parseToString(stream);
//        }
//    }

    static InputStream getInputStream() throws FileNotFoundException {
        InputStream initialStream = new FileInputStream(new File("src/main/resources/tika.docx"));
        return initialStream;
    }

    public static String detectDocTypeUsingDetector(InputStream stream) throws IOException {
        Detector detector = new DefaultDetector();
        Metadata metadata = new Metadata();
        MediaType mediaType = detector.detect(stream, metadata);
        return mediaType.toString();
    }

    public static String detectDocTypeUsingFacade(InputStream stream) throws IOException {
        Tika tika = new Tika();
        String mediaType = tika.detect(stream);
        return mediaType;
    }

    public static String extractContentUsingParser(InputStream stream) throws IOException, TikaException, SAXException {
        Parser parser = new AutoDetectParser();

        ContentHandler handler = new BodyContentHandler();

        Metadata metadata = new Metadata();

        ParseContext context = new ParseContext();

        parser.parse(stream, handler, metadata, context);

        return handler.toString();
    }

    public static String extractContentUsingFacade(InputStream stream) throws IOException, TikaException {
        Tika tika = new Tika();

        String content = tika.parseToString(stream);

        return content;
    }

    public static Metadata extractMetadataUsingParser(InputStream stream) throws IOException, SAXException, TikaException {

        Parser parser = new AutoDetectParser();

        ContentHandler handler = new BodyContentHandler();

        Metadata metadata = new Metadata();

        ParseContext context = new ParseContext();

        parser.parse(stream, handler, metadata, context);

        return metadata;
    }

    public static Metadata extractMetadataUsingFacade(InputStream stream) throws IOException, TikaException {

        Tika tika = new Tika();

        Metadata metadata = new Metadata();

        tika.parse(stream, metadata);

        return metadata;
    }

    public static String parseToHTML(InputStream stream) throws IOException, SAXException, TikaException {

        ContentHandler handler = new ToXMLContentHandler();

        AutoDetectParser parser = new AutoDetectParser();

        Metadata metadata = new Metadata();

        parser.parse(stream, handler, metadata);

        return handler.toString();

    }
}
