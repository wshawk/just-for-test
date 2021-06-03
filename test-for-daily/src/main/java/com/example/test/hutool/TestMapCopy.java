package com.example.test.hutool;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hawk
 * @package com.example.test.hutool
 * @desc
 * @date 2021/5/18
 */
public class TestMapCopy {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>(5);
        map.put("name", "test");
        map.put("a", "test");
        map.put("d", "test");
        map.put("s", "test");

        Map<String, String> test = new HashMap<>(5);
        for (Map.Entry<String, String> item : map.entrySet()){
            test.put(item.getKey(), item.getValue());
        }

        map.remove("a");

        System.out.println(test.get("a"));
    }
}
