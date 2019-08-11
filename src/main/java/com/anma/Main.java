package com.anma;

import com.anma.services.CreateDocument;
import org.apache.poi.xwpf.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

//        CreateDocument.createDocument();

        XWPFDocument document = new XWPFDocument();

        List<XWPFTable> tables = document.getTables();

        for (XWPFTable table : tables) {
            
        }

    }
}
