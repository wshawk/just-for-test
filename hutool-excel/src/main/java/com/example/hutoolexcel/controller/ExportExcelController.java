package com.example.hutoolexcel.controller;

import com.example.hutoolexcel.service.POIDemoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author wsHawk
 * @Title: ExportExcelController
 * @ProjectName poi-demo
 * @Description: TODO
 * @since 2020/10/28 21:32
 */
@RestController
public class ExportExcelController {

    @Resource
    POIDemoService poiDemoService;

    @GetMapping("/export")
    public void export(HttpServletResponse response){
        //获取开始时间
        long startTime = System.currentTimeMillis();
        poiDemoService.export(response);
        //获取结束时间
        long endTime = System.currentTimeMillis();
        //输出程序运行时间
        System.out.println("程序运行时间：" + (endTime - startTime)/1000 + "s");
    }

}
