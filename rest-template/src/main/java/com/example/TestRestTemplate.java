package com.example;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author hawk
 * @package com.example
 * @desc
 * @date 2021/3/20
 */
public class TestRestTemplate {
    public static void main(String[] args) {
        ClientHttpRequestFactory requestFactory;
        RestTemplate restTemplate = new RestTemplate();
        String url = "";
        // 获取access_token
        ResponseEntity<String> res = restTemplate.getForEntity(url, String.class);
        System.out.println(res.getBody());
        JSONObject body = JSON.parseObject(res.getBody());
        System.out.println("access_token: " + body.getString("access_token"));
        System.out.println("expires_in: " + body.getString("expires_in") + " s");

        // scene 1024

        String post_url = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token="+body.getString("access_token");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("scene","1024");
        HttpEntity<String> entity = new HttpEntity<>(jsonObject.toString(), headers);
        // 发送请求
        byte[] responseEntity = restTemplate.postForObject(post_url, entity, byte[].class);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(responseEntity);
        OutputStream outputStream = new ByteArrayOutputStream();

        try {
                FileOutputStream fileOutputStream = new FileOutputStream("/img.jpg");
                IOUtils.copy(inputStream, fileOutputStream);
            fileOutputStream.write(responseEntity);
            fileOutputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        // System.out.println(Arrays.toString(responseEntity));

    }
}
