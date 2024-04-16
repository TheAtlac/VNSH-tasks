package ru.nshi.service;

import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;

@Service
@AllArgsConstructor
public class FooService {
    public Resource export() throws IOException {
        StringBuilder filename = new StringBuilder("Foo Export").append(" - ")
                .append("Test 1.xlsx");
        return export(String.valueOf(filename));
    }

    public ByteArrayResource export(String filename) throws IOException {
        byte[] bytes = new byte[1024];
        try (Workbook workbook = generateExcel()) {
            FileOutputStream fos = write(workbook, filename);
            fos.write(bytes);
            fos.flush();
            fos.close();
        }

        return new ByteArrayResource(bytes);
    }

    private Workbook generateExcel() {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();

        //create columns and rows

        return workbook;
    }

    private FileOutputStream write(final Workbook workbook, final String filename) throws IOException {
        FileOutputStream fos = new FileOutputStream(filename);
        workbook.write(fos);
        fos.close();
        return fos;
    }
}