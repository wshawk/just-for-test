package com.example.test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author hawk
 * @package com.example.test
 * @desc
 * @date 2021/6/3
 */
public class TestStream {
    public static void main(String[] args) {
        Student a = new Student(1, 12, "a");
        Student b = new Student(1, 11, "b");
        Student c = new Student(1, 15, "c");
        Student d = new Student(1, 11, "d");

        List<Student>  list = new ArrayList<>();
        list.add(a);
        list.add(b);
        list.add(c);
        list.add(d);

        Map<Integer, List<Student>> map = list.stream()
                .sorted(Comparator.comparing(Student::getAge).reversed())
                .collect(Collectors.groupingBy(Student::getId));

        System.out.println(map.get(2));
    }
}
