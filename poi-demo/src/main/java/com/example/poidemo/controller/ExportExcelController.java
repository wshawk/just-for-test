package com.example.poidemo.controller;

import com.example.poidemo.service.POIDemoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.TreeSet;

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

    @GetMapping("/export_get")
    public void exportGet(HttpServletResponse response){
         poiDemoService.export(response);
    }
    @PostMapping("/export_post")
    public void exportPost(HttpServletResponse response){
         poiDemoService.export(response);
    }
}
