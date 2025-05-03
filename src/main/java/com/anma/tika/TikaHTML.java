package com.anma.tika;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
//import org.apache.tika.parser.html.HtmlParser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class TikaHTML {
    public static final String SRC_RESOURCES_TEST_1_HTML = "src/resources/test-1.html";

    public static void main(final String[] args) throws IOException, SAXException, TikaException {
        //detecting the file type
        BodyContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        FileInputStream inputstream = new FileInputStream(SRC_RESOURCES_TEST_1_HTML);
        ParseContext context = new ParseContext();

        //Html parser
//        HtmlParser htmlparser = new HtmlParser();
//        htmlparser.parse(inputstream, handler, metadata, context);

        System.out.println("Contents of the document:" + handler.toString());
        System.out.println("Metadata of the document:");

        String[] metadataNames = metadata.names();

        for(String name : metadataNames) {
            System.out.println(name + ":   " + metadata.get(name));
        }
    }
}
