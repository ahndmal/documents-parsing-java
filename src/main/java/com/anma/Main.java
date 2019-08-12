package com.anma;

import com.anma.services.CreateDocument;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBody;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class Main {

    public static String input = "AAAAAA.docx";

    public static void main(String[] args) throws IOException {

//        CreateDocument.createDocument();

        XWPFDocument document = null;

        try {

            document = new XWPFDocument(OPCPackage.open("AAAAAA.docx"));

            XWPFTable xwpfTable = document.getTables().get(0);

            XWPFTableRow xwpfTableRow = xwpfTable.getRow(0);

            XWPFParagraph xwpfParagraph = xwpfTableRow.getCell(0).getParagraphs().get(0);

//            CTBody ctbody = document.getDocument().getBody();

            xwpfParagraph.getBody();



        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }

    }
}
