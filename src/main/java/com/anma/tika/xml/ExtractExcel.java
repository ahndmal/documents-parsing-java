package com.anma.tika.xml;

import org.apache.tika.detect.DefaultDetector;
import org.apache.tika.detect.Detector;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.microsoft.ooxml.OOXMLParser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

import java.io.*;

public class ExtractExcel {

    public static void main(String[] args) throws IOException, TikaException, SAXException {

//        Parser parser = new AutoDetectParser();
        
        BodyContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        FileInputStream inputstream = new FileInputStream(new File("src/resources/excel_1.xlsx"));
        ParseContext pcontext = new ParseContext();

        OOXMLParser msofficeparser = new OOXMLParser ();
        msofficeparser.parse(inputstream, handler, metadata,pcontext);

        System.out.println(handler.toString());


    }

//     static String extractContentUsingParser(InputStream stream) throws IOException, TikaException, SAXException {
//
//
//
//        return handler.toString();
//    }
}
