package com.example.poidemo.poi;

import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @author wsHawk
 * @Title: ExportExcelPOIUtils
 * @ProjectName poi-demo
 * @Description: TODO Excel导出工具类
 * @since 2020/10/28 21:36
 */
public class ExportExcelPOIUtils {
    private static final Logger logger = LoggerFactory.getLogger(ExportExcelPOIUtils.class);
    /**
     *
     * @param list 需要导出的列表数据
     * @param titles 每一列的说明,按照属性声明的顺序传入
     * @return
     */
    public static <T> SXSSFWorkbook exportExcel(List<T> list, String[] titles) {
        try {
            // 阈值，内存中的对象数量最大值，超过这个值会生成一个临时文件存放到硬盘中
            SXSSFWorkbook wb = new SXSSFWorkbook(100);
            Sheet sheet = wb.createSheet();
            Row titleRow = sheet.createRow(0);
            for (int i = 0; i < titles.length; i++) {
                Cell cell = titleRow.createCell(i);
                cell.setCellValue(titles[i]);
            }

            // 3.从集合中取数据并赋值
            for (int i = 0; i < list.size(); i++) {
                Object obj = list.get(i);
                if (obj == null){
                    continue;
                }
                Class clazz = obj.getClass();
                Row row = sheet.createRow(i+1);
                Field[] fileds = clazz.getDeclaredFields();
               for (int j = 0; j < fileds.length; j++){
                   fileds[j].setAccessible(true);
                   row.createCell(j).setCellValue(fileds[j].get(obj).toString());
               }
            }
            return wb;
        }catch (Exception e){
            logger.error("导出excel失败："+e);
        }
        return  null;
    }
}
