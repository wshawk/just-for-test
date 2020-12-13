package com.example.poidemo.service.impl;

import com.example.poidemo.constant.Constant;
import com.example.poidemo.entity.TestEntity;
import com.example.poidemo.poi.ExportExcelPOIUtils;
import com.example.poidemo.service.POIDemoService;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author wsHawk
 * @Title: POIDemoServiceImpl
 * @ProjectName poi-demo
 * @Description: TODO
 * @since 2020/10/28 21:32
 */
@Service("pOIDemoService")
public class POIDemoServiceImpl implements POIDemoService {
    private static final Logger logger = LoggerFactory.getLogger(POIDemoServiceImpl.class);
    @Override
    public void export(HttpServletResponse response) {
        List<TestEntity> list = getTestEntityList(1048577);
        String[] titles = {"code","name","sex"};
        /**
         * 忽略不需要映射的字段
         */
        Set<String> ignoreFields = new HashSet<>();
        ignoreFields.add("serialVersionUID");

        SXSSFWorkbook sxssfWorkbook = ExportExcelPOIUtils.exportExcel(list,titles,ignoreFields);
        if (sxssfWorkbook == null){
            logger.error(Constant.EXPORT_EXCEL_ERROR);
            return;
        }
        OutputStream out = null;
        try {
            String fileName = URLEncoder.encode("测试excel.xlsx", "UTF-8");
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
            logger.error(Constant.EXPORT_EXCEL_ERROR+e);
        } finally {
            if (out != null){
                try {
                    out.close();
                }catch (Exception e){
                   logger.error(Constant.OUT_STREAM_CLOSE_ERROR+e);
                }
            }
        }
    }

    public List<TestEntity> getTestEntityList(int row) {
        List<TestEntity> testEntityList = new ArrayList<>();
        for (int i = Constant.ZERO_NUM; i < row; i++) {
            TestEntity testEntity = new TestEntity();
            testEntity.setCode(i);
            testEntity.setSex(i%2 == Constant.ZERO_NUM ? Constant.SEX_FOR_GIRL : Constant.SEX_FOR_BOY);
            testEntity.setName("张"+i);
            testEntityList.add(testEntity);
        }
        return testEntityList;
    }
}
