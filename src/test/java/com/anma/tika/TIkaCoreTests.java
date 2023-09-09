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
import org.apache.tika.sax.ContentHandlerDecorator;
import org.apache.tika.sax.ToXMLContentHandler;
import org.apache.tika.sax.XHTMLContentHandler;
import org.apache.tika.sax.xpath.Matcher;
import org.apache.tika.sax.xpath.MatchingContentHandler;
import org.apache.tika.sax.xpath.XPathParser;
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
import java.util.ArrayList;
import java.util.List;

public class TIkaCoreTests {
    private static final int MAXIMUM_TEXT_CHUNK_SIZE = 1000;
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
        try (InputStream stream = TikaInputStream.get(Path.of("src/resources/doc/file-sample_1MB.odt"))) {
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

        System.out.println(handler.toString());

        stream.close();
    }

    @Test
    void parseDocToHTML() throws IOException, SAXException, TikaException {
        ContentHandler handler = new ToXMLContentHandler();

        AutoDetectParser parser = new AutoDetectParser();
        Metadata metadata = new Metadata();
        try (InputStream stream = TikaInputStream.get(Path.of("src/resources/doc/file-sample_1MB.odt"))) {
            parser.parse(stream, handler, metadata);
            System.out.println(handler.toString());
        }
    }


    // Fetching just certain bits of the XHTML
    public String parseOnePartToHTML() throws IOException, SAXException, TikaException {
        // Only get things under html -> body -> div (class=header)
        XPathParser xhtmlParser = new XPathParser("xhtml", XHTMLContentHandler.XHTML);
        Matcher divContentMatcher = xhtmlParser.parse("/xhtml:html/xhtml:body/xhtml:div/descendant::node()");
        ContentHandler handler = new MatchingContentHandler(new ToXMLContentHandler(), divContentMatcher);

        AutoDetectParser parser = new AutoDetectParser();
        Metadata metadata = new Metadata();
//        try (InputStream stream = ContentHandlerExample.class.getResourceAsStream("test2.doc")) {
        try (InputStream stream = TikaInputStream.get(Path.of("src/resources/doc/file-sample_1MB.odt"))) {
            parser.parse(stream, handler, metadata);
            return handler.toString();
        }
    }

    // Streaming the plain text in chunks
    public List<String> parseToPlainTextChunks() throws IOException, SAXException, TikaException {
        final List<String> chunks = new ArrayList<>();
        chunks.add("");
        ContentHandlerDecorator handler = new ContentHandlerDecorator() {
            @Override
            public void characters(char[] ch, int start, int length) {
                String lastChunk = chunks.get(chunks.size() - 1);
                String thisStr = new String(ch, start, length);

                if (lastChunk.length() + length > MAXIMUM_TEXT_CHUNK_SIZE) {
                    chunks.add(thisStr);
                } else {
                    chunks.set(chunks.size() - 1, lastChunk + thisStr);
                }
            }
        };

        AutoDetectParser parser = new AutoDetectParser();
        Metadata metadata = new Metadata();
        try (InputStream stream = TIkaCoreTests.class.getResourceAsStream("test2.doc")) {
            parser.parse(stream, handler, metadata);
            return chunks;
        }
    }

}
