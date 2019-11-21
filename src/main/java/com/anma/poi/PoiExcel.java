package com.anma.poi;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

public class PoiExcel {

    private final static String FILE_NAME = "./src/resources/excel-3.xlsx";

    public static void main(String[] args) throws IOException, InvalidFormatException {

//        FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
//        Workbook workbook = new XSSFWorkbook(excelFile);
//        System.out.println(workbook.getNumberOfSheets());

        Workbook workbook = WorkbookFactory.create(new File(FILE_NAME));

        System.out.println("Workbook has " + workbook.getNumberOfSheets() + " Sheets : ");

//        Iterator<Sheet> sheetIterator = workbook.sheetIterator();
//
//        while (sheetIterator.hasNext()) {
//            Sheet sheet = sheetIterator.next();
//            System.out.println("=> " + sheet.getSheetName());
//        }

        for(Sheet sheet: workbook) {
            System.out.println("=> " + sheet.getSheetName());
        }


    }
}
