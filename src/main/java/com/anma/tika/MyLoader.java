package com.anma.tika;

import org.apache.tika.exception.TikaException;
import org.xml.sax.SAXException;

import java.io.IOException;

public class MyLoader {

    public static void main(String[] args) throws IOException {

        try {
//            System.out.println(TikaAnalysis.extractContentUsingFacade(TikaAnalysis.getInputStream()));
            System.out.println(TikaAnalysis.parseToHTML(TikaAnalysis.getInputStream()));

        } catch (TikaException | SAXException e) {
            e.printStackTrace();
        }
    }
}