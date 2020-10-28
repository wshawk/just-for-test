package com.example.poidemo.service.impl;

import com.example.poidemo.entity.TestEntity;
import com.example.poidemo.poi.ExportExcelPOIUtils;
import com.example.poidemo.service.POIDemoService;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wsHawk
 * @Title: POIDemoServiceImpl
 * @ProjectName poi-demo
 * @Description: TODO
 * @since 2020/10/28 21:32
 */
@Service("pOIDemoService")
public class POIDemoServiceImpl implements POIDemoService {

    @Override
    public String export(HttpServletResponse response) {
        List<TestEntity> list = getTestEntityList(50);
        String[] titles = {"code","name","sex"};
        SXSSFWorkbook sxssfWorkbook = ExportExcelPOIUtils.exportExcel(list,titles);
        OutputStream out = null;
        try {
            String fileName = URLEncoder.encode("测试excel.xls", "UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            // 这句话的意思，是让浏览器用utf8来解析返回的数据
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            //设置响应为excel
            response.setContentType("application/vnd.ms-excel");

            //将文件输出
            out = response.getOutputStream();
            sxssfWorkbook.write(out);
            out.flush();
        } catch (Exception e) {
            System.out.println("=========ERROR==========");
        } finally {
            if (out != null){
                try {
                    out.close();
                }catch (Exception e){
                    System.out.println("=========out close ERROR==========");
                }
            }
        }
        return "null";
    }

    public List<TestEntity> getTestEntityList(int row) {
        List<TestEntity> testEntityList = new ArrayList<>();
        for (int i = 1; i < row; i++) {
            TestEntity testEntity = new TestEntity();
            testEntity.setCode(i);
            testEntity.setSex(i%2 == 0 ? "girl" : "boy");
            testEntity.setName("张"+i);
            testEntityList.add(testEntity);
        }
        return testEntityList;
    }
}
