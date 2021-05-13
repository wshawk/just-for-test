package com.example.test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author hawk
 * @package com.example.test
 * @desc
 * @date 2021/5/13
 */
public class TestLocalDateTime {
    /**
     * 例如:2018-12-28 10:00:00
     */
    public static final String DATE_TIME = "yyyy-MM-dd HH:mm:ss";

    public static void main(String[] args) {
        LocalDateTime now = parse("2020-12-12 12:00:20", DATE_TIME);
        LocalDateTime test = parse("2020-12-12 12:00:10", DATE_TIME);

        System.out.println(now.compareTo(test) >= 0);
        System.out.println("------------------");
        List<String> list = new ArrayList<>();
        String res = list.stream().map(Objects::toString).collect(Collectors.joining("/"));
        System.out.println(res.equals(""));
    }
    /**
     * 解析字符串日期为LocalDateTime
     *
     * @param dateStr 日期字符串
     * @param pattern 格式
     * @return Date
     */
    public static LocalDateTime parse(String dateStr, String pattern) {
        return LocalDateTime.parse(dateStr, DateTimeFormatter.ofPattern(pattern));
    }
}
