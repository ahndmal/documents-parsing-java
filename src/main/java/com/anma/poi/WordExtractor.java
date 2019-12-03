package com.anma.poi;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.FileInputStream;

public class WordExtractor {

    public static void main(String[] args)throws Exception {

        XWPFDocument docx = new XWPFDocument(new FileInputStream("src/main/resources/create_paragraph.docx"));

        //using XWPFWordExtractor Class
        XWPFWordExtractor we = new XWPFWordExtractor(docx);

        System.out.println(we.getText());
    }
}
