package com.example.test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

/**
 * @author hawk
 * @package com.example.test
 * @desc
 * @date 2021/4/13
 */
public class TestMills {
    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now.toInstant(ZoneOffset.of("+8")).toEpochMilli());
        System.out.println(rfc3339Time(System.currentTimeMillis()));
        System.out.println(rfc3339Time(now.toInstant(ZoneOffset.of("+8")).toEpochMilli()));
    }
    public static String rfc3339Time(long time){
        ZonedDateTime zonedDateTime = TestMills.toZonedDateTime(new Date(time));
        return  TestMills.format(zonedDateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssxxx"));
    }
    public static ZonedDateTime toZonedDateTime(Date date){
        Objects.requireNonNull(date, "date");
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault());
    }
    public static String format(ZonedDateTime zonedDateTime, DateTimeFormatter formatter) {
        Objects.requireNonNull(zonedDateTime, "zonedDateTime");
        Objects.requireNonNull(formatter, "formatter");
        return zonedDateTime.format(formatter);
    }
}
