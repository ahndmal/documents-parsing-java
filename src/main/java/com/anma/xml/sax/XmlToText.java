//package com.anma.xml.sax;
//
//import jdk.internal.org.xml.sax.InputSource;
//import org.w3c.dom.Document;
//
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import java.io.StringReader;
//
//public class XmlToText {
//
//    public static Document loadXMLFromString(String xml) throws Exception {
//
//        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//
//        DocumentBuilder builder = factory.newDocumentBuilder();
//
//        InputSource inputSource = new InputSource(new StringReader(xml));
//
//        return builder.parse(inputSource.getEncoding());                                                                 // TODO Change
//    }
//}
