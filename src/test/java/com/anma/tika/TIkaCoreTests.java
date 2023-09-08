package com.anma.tika;

import org.apache.tika.Tika;
import org.apache.tika.config.TikaConfig;
import org.apache.tika.detect.Detector;
import org.apache.tika.exception.TikaException;
import org.apache.tika.io.TikaInputStream;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.image.ImageParser;
import org.apache.tika.sax.BodyContentHandler;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class TIkaCoreTests {
    private final Logger LOG = LoggerFactory.getLogger(TIkaCoreTests.class);

    @Test
    void parseToStringExample() throws IOException, SAXException, TikaException {
        Tika tika = new Tika();
        try (InputStream stream = TIkaCoreTests.class.getResourceAsStream("doc/tika.docx")) {
            var res = tika.parseToString(stream);
            System.out.println(res);
        }
    }

    @Test
    void parseExample() throws IOException, SAXException, TikaException {
        AutoDetectParser parser = new AutoDetectParser();
        BodyContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        try (InputStream stream = TIkaCoreTests.class.getResourceAsStream("test.doc")) {
            parser.parse(stream, handler, metadata);
            var res = handler.toString();
            System.out.println(res);
        }
    }

    @Test
    void coreElements() throws TikaException, IOException, SAXException {
        TikaConfig config = new TikaConfig("tika-config.xml");
        Detector detector = config.getDetector();
        Parser autoDetectParser = new AutoDetectParser(config);
    }

    @Test
    void parserCore() throws IOException, TikaException, SAXException {
        Parser parser = new ImageParser();

        InputStream stream = Files.newInputStream(Path.of("src/resources/sample.jpg"));

        var out = new FileOutputStream("src/resources/samplejpg-out");

        ContentHandler handler = new DefaultHandler();

        parser.parse(stream, handler, new Metadata(), new ParseContext());

        stream.close();
    }
}
