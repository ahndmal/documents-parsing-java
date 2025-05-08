package com.anma.poi;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;

public class DocCreationServiceImpl implements DocCreationService {

    public void createDocument(String body) throws URISyntaxException, IOException, InvalidFormatException {

        Workbook workbook = new XSSFWorkbook();

//        CreationHelper createHelper = workbook.getCreationHelper();

        Sheet sheet = workbook.createSheet("Table1");

        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
//        headerFont.setFontHeightInPoints((short) 14);
//        headerFont.setColor(IndexedColors.RED.getIndex());

        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        Row headerRow = sheet.createRow(0);

        for(int i = 0; i < 3; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(i);
            cell.setCellStyle(headerCellStyle);
        }

        Row row1 = sheet.createRow(1);

        Cell cell1 = row1.createCell(0);
        cell1.setCellValue("Id");

        Cell cell2 = row1.createCell(1);
        cell2.setCellValue("Title");

        Cell cell3 = row1.createCell(2);
        cell3.setCellValue(body);

        // Resize all columns to fit the content size
        for(int i = 0; i < 2; i++) {
            sheet.autoSizeColumn(i);
        }

        FileOutputStream fileOut = new FileOutputStream("excel/Table1.xlsx");
        workbook.write(fileOut);

        fileOut.close();

        workbook.close();


    }
}
