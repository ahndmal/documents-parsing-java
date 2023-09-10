package com.anma.tika.pdf;

import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.apache.tika.io.TikaInputStream;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.html.HtmlMapper;
import org.apache.tika.parser.html.IdentityHtmlMapper;
import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.sax.BodyContentHandler;
import org.apache.tika.sax.ToHTMLContentHandler;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class TikaPdfParsingTest {
    private static final String PDF_FILE = "/home/andrii/Documents/pdf/Site_FS.pdf";

    @Test
    void parsePdf() throws IOException, TikaException, SAXException {
        PDFParser parser = new PDFParser();

        var is = TikaInputStream.get(Path.of(PDF_FILE));

//        BodyContentHandler handler = new BodyContentHandler();
          var handler = new ToHTMLContentHandler();

        ParseContext context = new ParseContext();
        context.set(HtmlMapper.class, new IdentityHtmlMapper());

        parser.parse(is, handler, new Metadata(), context);

        System.out.println(handler);
        
    }
}