package com.example;

import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hawk
 * @package com.example
 * @desc
 * @date 2021/3/23
 */
public class TestRequestEntity {
    public static void main(String[] args) throws URISyntaxException {
        URI uri = new URI("http://127.0.0.1:39616/mallv2/cart/payNotify");
        Map<String, String> map = new HashMap<>();
        map.put("a", "AAAA");
        RequestEntity.post(uri).contentType(MediaType.APPLICATION_JSON).body(map);
        RestTemplate restTemplate = new RestTemplate();
        // restTemplate.exchange()
        System.out.println();
    }
}
