package com.example.hutoolexcel.controller;

import com.example.hutoolexcel.util.ExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hawk
 * @package com.example.hutoolexcel.controller
 * @desc
 * @date 2021/3/10
 */
@RestController
public class ImportExcelController {
    @PostMapping("/import")
    public String importExcel(@RequestParam("file") MultipartFile file){
        try {
            InputStream inputStream = file.getInputStream();
            Workbook wb;
            if (file.getOriginalFilename().matches("^.+\\.(?i)(xls)$")
                    || file.getOriginalFilename().matches("^.+\\.(?i)(csv)$")) {
                wb = new HSSFWorkbook(inputStream);
            } else if (file.getOriginalFilename().matches("^.+\\.(?i)(xlsx)$")) {
                wb = new XSSFWorkbook(inputStream);
            } else {
                return "上传文件格式错误";
            }
            List<List<String>> excelResult = new ArrayList<>();
            DataFormatter formatter = new DataFormatter();
            for (Sheet sheet: wb){
                int rowStart = Math.min(2, sheet.getFirstRowNum());
                int rowEnd = sheet.getLastRowNum();
                for (int rowNum = rowStart; rowNum < rowEnd; rowNum++){
                    Row row = sheet.getRow(rowNum);
                    if (row == null) {
                        continue;
                    }
                    int lastColumn = row.getLastCellNum();
                    for (int cn=0; cn<lastColumn; cn++){
                        Cell cell = row.getCell(cn);
                        if (cell == null) {
                            continue;
                        }
                        CellReference cellRef = new CellReference(row.getRowNum(), cell.getColumnIndex());
                        System.out.print(cellRef.formatAsString());
                        System.out.print(" - ");
                        // get the text that appears in the cell by getting the cell value and applying any data formats (Date, 0.00, 1.23e9, $1.23, etc)
                        String text = formatter.formatCellValue(cell);
                        System.out.println(text);
                        // Alternatively, get the value and format it yourself
                        if (cell.getCellType().equals(CellType.STRING)){
                            System.out.println(cell.getRichStringCellValue().getString());
                        }else if (cell.getCellType().equals(CellType.NUMERIC)){
                            if (DateUtil.isCellDateFormatted(cell)) {
                                System.out.println(cell.getLocalDateTimeCellValue());
                            }
                            // else {
                            //     System.out.println(cell.getNumericCellValue());
                            // }
                        }else if (cell.getCellType().equals(CellType.BOOLEAN)){
                            System.out.println(cell.getBooleanCellValue());
                        }else if (cell.getCellType().equals(CellType.FORMULA)){
                            System.out.println(cell.getCellFormula());
                        }else if (cell.getCellType().equals(CellType.BLANK)){
                            System.out.println();
                        }else{
                            System.out.println();
                        }
                    }
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return "sucess";
    }
}
