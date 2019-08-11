package com.anma;

import org.apache.poi.xwpf.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;

public class Main {

    public static String logo = "logo-leaf.png";
    public static String paragraph1 = "poi-word-para1.txt";
    public static String paragraph2 = "poi-word-para2.txt";
    public static String paragraph3 = "poi-word-para3.txt";
    public static String output = "AAAAAA.docx";

    public static void main(String[] args) throws IOException {

        XWPFDocument document = new XWPFDocument();

        XWPFParagraph title = document.createParagraph();

        title.setAlignment(ParagraphAlignment.CENTER);

        XWPFRun titleRun = title.createRun();

        titleRun.setText("Build Your REST API with Spring");
        titleRun.setColor("009933");
        titleRun.setBold(true);
        titleRun.setFontFamily("Courier");
        titleRun.setFontSize(20);

        XWPFParagraph subTitle = document.createParagraph();
        subTitle.setAlignment(ParagraphAlignment.CENTER);

        XWPFRun subTitleRun = subTitle.createRun();
        subTitleRun.setText("from HTTP fundamentals to API Mastery");
        subTitleRun.setColor("00CC44");
        subTitleRun.setFontFamily("Courier");
        subTitleRun.setFontSize(16);
        subTitleRun.setTextPosition(20);
        subTitleRun.setUnderline(UnderlinePatterns.DOT_DOT_DASH);

// Formatting paragraphs

//        XWPFParagraph para1 = document.createParagraph();
//        para1.setAlignment(ParagraphAlignment.BOTH);
//        String string1 = Helper.convertTextFileToString(para1);
//        XWPFRun para1Run = para1.createRun();
//        para1Run.setText(string1);

        FileOutputStream fileOutputStream = new FileOutputStream(output);

        document.write(fileOutputStream);
        fileOutputStream.close();
        document.close();

    }
}
