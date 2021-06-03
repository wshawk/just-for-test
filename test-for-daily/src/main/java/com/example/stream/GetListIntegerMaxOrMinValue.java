package com.example.stream;

import cn.hutool.core.lang.Console;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author hawk
 * @package com.example.stream
 * @desc
 * @date 2021/6/3
 */
public class GetListIntegerMaxOrMinValue {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        while (list.size() < 10){
            list.add(new Random().nextInt(10) + 1);
        }
        Console.log("list is:", list);
        Console.log("max value is:", list.stream().reduce(Integer::max).orElse(0));
        Console.log("min value is:", list.stream().reduce(Integer::min).get());

        Console.log("max value is:", list.stream().max(Integer::compareTo).get());
        Console.log("max value is:", list.stream().mapToInt(Integer::intValue).max().getAsInt());
    }
}
