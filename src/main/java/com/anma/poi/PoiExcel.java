package com.anma.poi;

import com.anma.models.Person;
import com.anma.mongo.DBConnector;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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

        Sheet sheet = workbook.getSheetAt(0);

        DataFormatter dataFormatter = new DataFormatter();

        DBConnector dbConnector = new DBConnector();

        Map<String, String> objectsMap = new HashMap<>();

        for (Row row : sheet) {

            Person person = new Person();

            for(Cell cell: row) {

                String cellValue = dataFormatter.formatCellValue(cell);

                if (cellValue.equals("First Name")
                        || cellValue.equals("Last Name")
                        || cellValue.equals("Age")) { continue;
                }
                person.setFirstName(cellValue);

                System.out.print(cellValue + "\t");
            }
            dbConnector.addObject(objectsMap, "persons", "persons");
            System.out.println();
        }


    }
}
