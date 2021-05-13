package com.example.test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author hawk
 * @package com.example.test
 * @desc
 * @date 2021/5/12
 */
public class TestStreamStringSort {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("5");
        list.add("3");
        list.add("8");
        list.add("1");
        list.add("9");
        list.add("2");
        list.add("2");
        list.stream().distinct().sorted().collect(Collectors.toList()).forEach(System.out::println);
    }
}
