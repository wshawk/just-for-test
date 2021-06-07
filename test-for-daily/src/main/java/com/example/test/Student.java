package com.example.test;

import lombok.Data;

/**
 * @author hawk
 * @package com.example.test
 * @desc
 * @date 2021/6/3
 */
@Data
public class Student {
    private Integer id;
    private String name;
    private Integer age;

    public Student(Integer id,  Integer age, String name){
        this.age = age;
        this.id = id;
        this.name = name;
    }
}
