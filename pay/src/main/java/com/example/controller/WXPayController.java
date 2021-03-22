package com.example.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author hawk
 * @package com.example
 * @desc
 * @date 2021/3/17
 */
@RestController
@RequestMapping("/wx")
public class WXPayController {

    @Resource
    RestTemplate restTemplate;

    @PostMapping("/h5")
    String test (){
        return null;
    }

    @PostMapping("/rest_post")
    void restPost(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name","test");
        HttpEntity<String> entity = new HttpEntity<>(jsonObject.toString(), headers);
        String url = "http://127.0.0.1:39616/mallv2/cart/payNotify";
        // 发送请求
        String responseEntity = restTemplate.postForObject(url, entity, String.class);
        System.out.println(responseEntity);
    }

    @GetMapping("/rest_get")
    void restGet(){
        String url = "http://127.0.0.1:39616/mallv2/cart/payNotify";
        // 发送请求
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        System.out.println(responseEntity);
    }
}
