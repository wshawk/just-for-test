package com.example.test;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hawk
 * @package com.example.test
 * @desc
 * @date 2021/3/16
 */
public class TestMap {
    public static void main(String[] args) {
        Map map = new HashMap<>();
        map.put("s", 15);
        map.put("x", "2ss");

        Integer s = (Integer) map.get("s");
        String v = (String) map.get("x");
        System.out.println(s);
        System.out.println(v);
        Date date = new Date();
        LocalDateTime test = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        // LocalTime.
        // System.out.println(date);
    }
}
