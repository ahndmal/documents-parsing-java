package com.anma.tika;

import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.xml.XMLParser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ExtractXml {

    private static final String INPUT = "AAAAAA.docx";

    public static void main(String[] args) throws IOException, TikaException, SAXException {

        BodyContentHandler handler = new BodyContentHandler();

        Metadata metadata = new Metadata();

        FileInputStream inputstream = new FileInputStream(new File("src/resources/dogs.xml"));

//        InputStream stream = this.getClass().getClassLoader().getResourceAsStream("tika.docx");

        ParseContext pcontext = new ParseContext();

        XMLParser xmlparser = new XMLParser();

        xmlparser.parse(inputstream, handler, metadata, pcontext);

        System.out.println("Contents of the document:" + handler.toString());
        System.out.println("Metadata of the document:");
        String[] metadataNames = metadata.names();

        for(String name : metadataNames) {
            System.out.println(name + ": " + metadata.get(name));
        }

//        File file = new File("src/resources/dogs.xml");
//
//        Tika tika = new Tika();
//
//        String filecontent = tika.parseToString(file);
//
//        System.out.println(filecontent);


    }
}
