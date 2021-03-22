package com.example.hutoolexcel.util;


import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hawk
 * @package com.example.hutoolexcel.util
 * @desc
 * @date 2021/3/19
 */
public class ExcelUtil {
    private Logger logger = LoggerFactory.getLogger(ExcelUtil.class);

    private HSSFWorkbook workbook;

    /**
     * 文件输入流方式获取文件
     *
     * @param excelFile
     * @throws IOException
     */
    public ExcelUtil(File excelFile) throws IOException {
        workbook = new HSSFWorkbook(new FileInputStream(excelFile));
    }

    /**
     * 纯输入流方式获取文件
     *
     * @param is
     * @throws IOException
     */
    public ExcelUtil(InputStream is) throws IOException {
        workbook = new HSSFWorkbook(is);
    }

    /**
     * 获得表中的数据
     *
     * @param sheetNum 表格索引 从0开始
     * @param isAddId  是否自动增加id
     * @return
     * @throws IOException
     */
    public List<String[]> getDatasFromSheet(int sheetNum, boolean isAddId) throws IOException {
        // 表格数据对象
        List<String[]> resultList = new ArrayList<String[]>();
        // 获得指定的表
        HSSFSheet sheet = workbook.getSheetAt(sheetNum);
        // 获得数据总行数
        int rowCount = sheet.getLastRowNum();
        logger.info("excel表总行数: " + rowCount);
        if (rowCount < 1) {
            return null;
        }
        // 逐行读取数据
        for (int rowIndex = 0; rowIndex <= rowCount; rowIndex++) {
            // 获得行对象
            HSSFRow row = sheet.getRow(rowIndex);
            if (row == null) {
                continue;
            }
            // 本行单元格的个数
            int columnCount = row.getLastCellNum();
            if (isAddId) {
                columnCount = row.getLastCellNum() + 1;
            }
            String[] rowData = getRowDatas(row, columnCount, isAddId);
            if (rowData != null) {
                resultList.add(rowData);
            }
        }
        return resultList;
    }

    /**
     * 获得表中的数据
     *
     * @param sheetNum  表格索引 从0开始
     * @param columnNum 每行数据个数
     * @param isAddId   是否自动增加id
     * @return
     * @throws IOException
     */
    public List<String[]> getDatasFromSheet(int sheetNum, int columnNum, boolean isAddId) throws IOException {
        // 表格数据对象
        List<String[]> resultList = new ArrayList<String[]>();
        // 获得指定的表
        HSSFSheet sheet = workbook.getSheetAt(sheetNum);
        // 获得数据总行数
        int rowCount = sheet.getLastRowNum();
        logger.info("excel表总行数: " + rowCount);
        if (rowCount < 1) {
            return null;
        }
        if (isAddId) {
            columnNum++;
        }
        // 逐行读取数据
        for (int rowIndex = 0; rowIndex <= rowCount; rowIndex++) {
            // 获得行对象
            HSSFRow row = sheet.getRow(rowIndex);
            if (row == null) {
                continue;
            }
            String[] rowData = getRowDatas(row, columnNum, isAddId);
            if (rowData != null) {
                resultList.add(rowData);
            }
        }
        return resultList;
    }

    /**
     * 获得表中的数据
     *
     * @param sheetNumber 表格索引 从0开始
     * @param flag        flag=0 不增加id,flag=1自动增加id
     * @return
     * @throws IOException
     */
    public List<Object[]> getNewDatasInSheet(int sheetNumber, int flag) throws IOException {
        List<Object[]> result = new ArrayList<Object[]>();
        // 获得指定的表
        HSSFSheet sheet = workbook.getSheetAt(sheetNumber);
        // 获得数据总行数
        int rowCount = sheet.getLastRowNum();
        //int rowCount=this.getRightRows(sheetAs);
        int sjrsCols = 0;
        logger.info("found excel rows count: " + rowCount);
        if (rowCount < 1) {
            return null;
        }
        // 逐行读取数据
        for (int rowIndex = 0; rowIndex <= rowCount; rowIndex++) {
            // 获得行对象
            HSSFRow row = sheet.getRow(rowIndex);
            if (row != null) {
                // 获得本行中单元格的个数
                int columnCount = 0;
                if (flag == 0) {
                    columnCount = row.getLastCellNum();
                } else {
                    columnCount = row.getLastCellNum() + 1;
                }
                Object[] rowData = new Object[columnCount];
                // 获得本行中各单元格中的数据
                if (flag == 0) {
                    for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
                        HSSFCell cell = row.getCell(columnIndex);
                        // 获得指定单元格中数据
                        Object cellStr = this.getCellString(cell);
                        if (cellStr != null) {
                            if (cellStr.toString().trim().length() > 0) {
                                rowData[columnIndex] = cellStr.toString().trim();
                                sjrsCols++;
                            }
                        }
                    }
                } else {
                    for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
                        if (columnIndex == 0) {
                            // rowData[0] = KeyGenerator.getUUID();
                        } else {
                            // 获得指定单元格中数据
                            HSSFCell cell = row.getCell(columnIndex - 1);
                            Object cellStr = this.getCellString(cell);
                            if (cellStr != null) {
                                if (cellStr.toString().trim().length() > 0) {
                                    rowData[columnIndex] = cellStr.toString().trim();
                                    sjrsCols++;
                                }
                            }
                        }
                    }
                }
                if (sjrsCols != 0) {
                    result.add(rowData);
                }
                sjrsCols = 0;
            }
        }
        return result;
    }

    /**
     * 获取单行单元格数据
     * @param row
     * @param columnNum
     * @param isAddId
     * @return
     */
    private String[] getRowDatas(HSSFRow row, int columnNum, boolean isAddId) {
        String[] rowData = new String[columnNum];
        boolean hasData = false;
        for (int i = 0; i < columnNum; i++) {
            int index = i;
            if (isAddId) {
                index = i - 1;
                if (i == 0) {
                    // rowData[0] = KeyGenerator.getUUID();
                }
            }
            HSSFCell cell = row.getCell(index);
            // 获得指定单元格中数据
            String cellStr = this.getCellString(cell);
            if (!StringUtils.isEmpty(cellStr)) {
                rowData[i] = cellStr;
                hasData = true;
            }
        }
        if (hasData) {
            return rowData;
        } else {
            return null;
        }
    }

    /**
     * 获得单元格中的内容
     *
     * @param cell 单元格对象
     * @return
     */
    protected String getCellString(HSSFCell cell) {
        String result = null;
        // if (cell != null) {
        //     int cellType = cell.getCellType().getCode();
        //     switch (cellType) {
        //         case CellType.STRING.getCode():
        //             result = cell.getRichStringCellValue().getString();
        //             break;
        //         case CellType.NUMERIC:
        //             DecimalFormat df = new DecimalFormat("#");
        //             result = df.format(cell.getNumericCellValue());
        //             break;
        //         case CellType.FORMULA:
        //             result = cell.getCellFormula();
        //             break;
        //         case CellType.ERROR:
        //             result = null;
        //             break;
        //         case CellType.BOOLEAN:
        //             result = cell.getBooleanCellValue() ? "true" : "false";
        //             break;
        //         case CellType.BLANK:
        //             result = null;
        //             break;
        //     }
        //     if (result != null) {
        //         result = result.trim();
        //     }
        // }
        return result;
    }
}
// public class ExcelUtil {
//     public static void readExcel(MultipartFile file){
//         try {
//             List<String> temp = new ArrayList<>();
//
//             InputStream fileIn = file.getInputStream();
//             Workbook wb = null;
//             if (checkVersion(file.getOriginalFilename()))
//                 wb = new XSSFWorkbook(fileIn);  //xlsx
//             else {
//                 wb = new HSSFWorkbook(fileIn);  //xls
//             }
//
//             Sheet sht0 = wb.getSheetAt(0);
//
//             for (Row r : sht0) {
//                 if (r.getRowNum() >= 1) {
//
//                     //String 数据
//                     if ((r.getCell(0) != null) && (r.getCell(0).toString() != "")) {
//                         System.out.println(r.getCell(0).getStringCellValue().trim());
//                         temp.add(r.getCell(0).getStringCellValue().trim());
//                     }
//
//                     //number数据
//                     if ((r.getCell(1) != null) && (r.getCell(1).toString() != "")) {
//                         System.out.println((int) r.getCell(1).getNumericCellValue());
//                     }
//
//                     //未知数据类型数据
//                     if ((r.getCell(1) != null) && (r.getCell(1).toString() != "")) {
//                         System.out.println(r.getCell(1).toString());
//                     }
//
//                 }
//             }
//             fileIn.close();
//             wb.close();
//
//             return ;
//         } catch (Exception e) {
//             // log.error(e.toString(), e);
//             System.out.println(e);
//         }
//         return ;
//     }
//     public static boolean checkVersion(String fileName) {
//         if(fileName.matches("^.+\\.(?i)(xlsx)$")) {
//             return true;
//         }
//         return false;
//     }
// }
