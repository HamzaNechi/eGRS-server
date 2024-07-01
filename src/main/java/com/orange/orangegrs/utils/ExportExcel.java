package com.orange.orangegrs.utils;

import com.orange.orangegrs.entities.Visite;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class ExportExcel {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Visite> listVisits;

    public ExportExcel(List<Visite> listVisits) {
        this.listVisits = listVisits;
        workbook = new XSSFWorkbook();
    }

    private void writeHeaderLine() {
        sheet = workbook.createSheet("Les visites terrain");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        createCell(row, 0, "Visite ID", style);
        createCell(row, 1, "Site", style);
        createCell(row, 2, "Date", style);
        createCell(row, 3, "Index OTN", style);
        createCell(row, 4, "Index OO", style);
        createCell(row, 5, "Index TT", style);
        createCell(row, 6, "Responsable", style);
    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else if (value instanceof Long) {
            cell.setCellValue((Long) value);
        } else if (value instanceof Double) {
            cell.setCellValue((Double) value);
        } else if (value instanceof Date) {
            cell.setCellValue(value.toString()); // Vous pouvez formater la date si nécessaire
        } else {
            cell.setCellValue(value.toString());
        }
        cell.setCellStyle(style);
    }

    private void writeDataLines() {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (Visite visite : listVisits) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, visite.getVisiteId(), style);
            createCell(row, columnCount++, visite.getSite().getSiteCode(), style);
            createCell(row, columnCount++, visite.getDateInsertion(), style);
            createCell(row, columnCount++, visite.getIndexCompteur(), style);
            createCell(row, columnCount++, visite.getIndexOO(), style);
            createCell(row, columnCount++, visite.getIndexTT(), style);
            createCell(row, columnCount++, visite.getLogin().getLogin(), style);
        }
    }

    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();
    }
}