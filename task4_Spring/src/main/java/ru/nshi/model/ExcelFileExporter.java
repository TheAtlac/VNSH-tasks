package ru.nshi.model;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;

public class ExcelFileExporter {

    public static ByteArrayInputStream contactListToExcelFile() {
        try(Workbook workbook = new XSSFWorkbook()){
            Sheet sheet = workbook.createSheet("Customers");

            Row row = sheet.createRow(0);
            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex());
            CellStyle centerStyle = workbook.createCellStyle();
            centerStyle.setAlignment(CellStyle.ALIGN_CENTER);
            centerStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
//            headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            // Creating header
            CellStyle s = workbook.createCellStyle();
            CreationHelper createHelper = workbook.getCreationHelper();
            s.setDataFormat(
                    createHelper.createDataFormat().getFormat("d/m/yy h:mm"));
            Cell cell = row.createCell(0);
            cell.setCellValue("First Name");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(1);
            cell.setCellValue("Last Name");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(2);
            cell.setCellValue("Mobile");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(3);
            cell.setCellValue("Email");
            cell.setCellStyle(headerCellStyle);

            sheet.addMergedRegion(new CellRangeAddress(5, 7, 0, 0));
            row = sheet.createRow(5);
            cell = row.createCell(0);
            cell.setCellValue("Can I?");
            cell.setCellStyle(centerStyle);

            Row dataRow = sheet.createRow(1);
            dataRow.createCell(0).setCellValue(123);
            for (int i = 0; i < 5; i++) {
                Row roww = sheet.createRow(i);
                for (int j = 0; j < 3; j++) {
                    roww.createCell(j).setCellValue(j+i);
                }
            }
            // Making size of column auto resize to fit with data
            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);
            sheet.autoSizeColumn(3);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            return new ByteArrayInputStream(outputStream.toByteArray());
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}