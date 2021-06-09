package com.example.test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author hawk
 * @package com.example.test
 * @desc
 * @date 2021/6/7
 */
public class GetANumberEveryBit {
    public static void main(String[] args) {
        int a = 123;

        List<Integer> list = new ArrayList<>();
        while (Math.abs(a) >= 1){
            int temp = a % 10;
            list.add(temp);
            list = list.stream().map(x -> x*=10).collect(Collectors.toList());
            a = a / 10;
        }
        System.out.println(list.stream().mapToInt(Integer::intValue).sum()/10);
    }
}
