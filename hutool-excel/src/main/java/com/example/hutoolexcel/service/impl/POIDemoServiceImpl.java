package com.example.hutoolexcel.service.impl;

import ch.qos.logback.classic.Logger;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.example.hutoolexcel.entity.TestEntity;
import com.example.hutoolexcel.service.POIDemoService;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
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
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(POIDemoServiceImpl.class);
    @Override
    public void export(HttpServletResponse response) {
        List<TestEntity> list = getTestEntityList(50000);
        List<TestEntity> rows = CollUtil.newArrayList(list);
        // 通过工具类创建writer
        ExcelWriter writer = ExcelUtil.getWriter("d:/writeBeanTest.xlsx");
        // 一次性写出内容，使用默认样式，强制输出标题
        writer.write(rows, true);
        //response为HttpServletResponse对象
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
//test.xls是弹出下载对话框的文件名，不能为中文，中文请自行编码
        response.setHeader("Content-Disposition","attachment;filename=test.xls");
       try {
           ServletOutputStream out=response.getOutputStream();
           writer.flush(out,true);
           // 关闭writer，释放内存
           writer.close();
           IoUtil.close(out);
       }catch (Exception e){
           logger.error("hutool导出失败:"+e);
       }

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
