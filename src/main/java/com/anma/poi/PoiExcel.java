package com.anma.poi;

import com.anma.models.Person;
import com.anma.mongo.DBConnectorOld;
import com.anma.mongo.DbConnector;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PoiExcel {

    private final static String FILE_NAME = "./src/resources/persons-excel.xlsx";

    public static void main(String[] args) throws IOException, InvalidFormatException {

//        FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
//        Workbook workbook = new XSSFWorkbook(excelFile);
//        System.out.println(workbook.getNumberOfSheets());

        Workbook workbook = WorkbookFactory.create(new File(FILE_NAME));

//        Iterator<Sheet> sheetIterator = workbook.sheetIterator();
//
//        while (sheetIterator.hasNext()) {
//            Sheet sheet = sheetIterator.next();
//            System.out.println("=> " + sheet.getSheetName());
//        }

//        for(Sheet sheet: workbook) {
//            System.out.println("=> " + sheet.getSheetName());
//        }

        Sheet sheet = workbook.getSheetAt(0);

        DataFormatter dataFormatter = new DataFormatter();

//        DBConnectorOld dbConnectorOld = new DBConnectorOld();
        DbConnector connector = new DbConnector("persons", "persons");

        Map<String, String> objectsMap = new HashMap<>();

        List<String> firstRowData = new ArrayList<>();
        List<Person> personList = new ArrayList<>();

        Row firstRow = sheet.getRow(0);

        int rowsNum = sheet.getPhysicalNumberOfRows();
        System.out.println("Number of rows: " + rowsNum);

        for (Cell cell: firstRow) {
            firstRowData.add(cell.toString());
            System.out.println(cell);
        }

        List<String> cellsData = new ArrayList<>();

        for (int i = 1; i < rowsNum; i++) {

            Row row = sheet.getRow(i);
            Person person = new Person();

//            for (int g = 0; g < row.getPhysicalNumberOfCells(); g++) {
//                cellsData.add(row.getCell(g).toString());
                person.setFirstName(row.getCell(0).toString());
                person.setLastName(row.getCell(1).toString());
                person.setAge(row.getCell(2).toString());
                personList.add(person);
                connector.addPerson(row.getCell(0).toString(), row.getCell(1).toString() ,row.getCell(2).toString());

//                for (Cell cell : sheet.getRow(i).getCell) {
//                    System.out.println(cell);
//                }
//            }
        }

        for (Person p : personList) {
            System.out.println("========");
            System.out.println(p.getFirstName());
            System.out.println(p.getLastName());
            System.out.println(p.getAge());
        }

//        for (Row row : sheet) {
//
//            Person person = new Person();
//
//            for(Cell cell: row) {
//
//                String cellValue = dataFormatter.formatCellValue(cell);
//
//                if (cellValue.equals("First Name")
//                        || cellValue.equals("Last Name")
//                        || cellValue.equals("Age")) { continue;
//                }
//                person.setFirstName(cellValue);
//
//                System.out.print(cellValue + "\t");
//            }
////            dbConnectorOld.addObject(objectsMap, "persons", "persons");
//            System.out.println();
//        }


    }
}
