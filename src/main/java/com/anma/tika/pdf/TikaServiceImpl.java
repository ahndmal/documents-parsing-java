package com.anma.tika.pdf;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.txt.TXTParser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TikaServiceImpl implements TikaService {

    @Override
    public String convertToText(String doc) throws FileNotFoundException {
        BodyContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        FileInputStream inputstream = new FileInputStream(doc);
        ParseContext pcontext = new ParseContext();

        //Text document parser
        TXTParser txtParser = new TXTParser();
        try {
            txtParser.parse(inputstream, handler, metadata, pcontext);
        } catch (IOException | SAXException | TikaException e) {
            e.printStackTrace();
        }
//        System.out.println("Contents of the document:" + handler.toString());
//        System.out.println("Metadata of the document:");
//        String[] metadataNames = metadata.names();
//
//        for (String name : metadataNames) {
//            System.out.println(name + " : " + metadata.get(name));
//        }

        return handler.toString();
    }

    @Override
    public String convertToHTML(String doc) {
        return "";
    }

    @Override
    public String getMetadata(String doc) {

        return "";
    }
}
