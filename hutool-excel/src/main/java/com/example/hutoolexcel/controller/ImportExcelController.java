package com.example.hutoolexcel.controller;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

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
            if (file.getOriginalFilename().matches("^.+\\.(?i)(xls)$")) {
                wb = new HSSFWorkbook(inputStream);
            } else if (file.getOriginalFilename().matches("^.+\\.(?i)(xlsx)$")) {
                wb = new XSSFWorkbook(inputStream);
            } else {
                return "上传文件格式错误";
            }

            //获得excel中第一个表格
            Sheet sheet = wb.getSheetAt(0);
            boolean flag = true;
            for (Row cells : sheet) {
                //跳过第一行
                if (flag) {
                    flag = false;
                    continue;
                }

                System.out.println("第1个字段：----" + cells.getCell(0));
                System.out.println("第2个字段：----" + cells.getCell(1));
                System.out.println("第3个字段：----" + cells.getCell(2));
                System.out.println("第4个字段：----" + cells.getCell(3));
                System.out.println("第5个字段：----" + cells.getCell(4));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return "sucess";
    }
}
