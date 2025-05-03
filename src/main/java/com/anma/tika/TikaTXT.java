package com.anma.tika;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.txt.TXTParser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

import java.io.FileInputStream;
import java.io.IOException;

public class TikaTXT {
    public static final String SRC_RESOURCES_TEST_1_TXT = "src/resources/test-1.txt";

    public static void main(final String[] args) throws IOException, SAXException, TikaException {
        //detecting the file type
        BodyContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        FileInputStream inputstream = new FileInputStream(SRC_RESOURCES_TEST_1_TXT);
        ParseContext pcontext = new ParseContext();

        //Text document parser
        TXTParser TexTParser = new TXTParser();
        TexTParser.parse(inputstream, handler, metadata, pcontext);
        System.out.println("Contents of the document:" + handler.toString());
        System.out.println("Metadata of the document:");
        String[] metadataNames = metadata.names();

        for (String name : metadataNames) {
            System.out.println(name + " : " + metadata.get(name));
        }
    }
}
