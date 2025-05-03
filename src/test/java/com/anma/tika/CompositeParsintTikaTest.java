package com.anma.tika;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.mime.MediaType;
import org.apache.tika.parser.CompositeParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
//import org.apache.tika.parser.html.HtmlParser;
import org.apache.tika.parser.txt.TXTParser;
import org.apache.tika.parser.xml.XMLParser;
import org.apache.tika.sax.ToHTMLContentHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

class CompositeParsintTikaTest {
    public static final String SRC_RESOURCES_TEST_1_HTML = "src/resources/test-1.html";

    @Test
    void compositeParsing() throws IOException, TikaException, SAXException {
        Map<MediaType, Parser> parsersByType = new HashMap<MediaType, Parser>();
//        parsersByType.put(MediaType.parse("text/html"), new HtmlParser());
        parsersByType.put(MediaType.parse("application/xml"), new XMLParser());

        CompositeParser parser = new CompositeParser();
        parser.setParsers(parsersByType);
        parser.setFallback(new TXTParser());

        Metadata metadata = new Metadata();
        metadata.set(Metadata.CONTENT_TYPE, "text/html");

        var stream = Files.newInputStream(Path.of(SRC_RESOURCES_TEST_1_HTML));

        var handler = new ToHTMLContentHandler();

        ParseContext context = new ParseContext();
        context.set(Locale.class, Locale.ENGLISH);

        parser.parse(stream, handler, metadata, context);

        System.out.println(handler);

        Assertions.assertNotNull(handler);
    }
}