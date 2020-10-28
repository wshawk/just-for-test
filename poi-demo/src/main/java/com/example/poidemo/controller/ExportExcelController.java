package com.example.poidemo.controller;

import com.example.poidemo.service.POIDemoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

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
    public String export(HttpServletResponse response){
        return poiDemoService.export(response);
    }

}
