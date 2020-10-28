package com.example.poidemo.entity;

/**
 * @author wsHawk
 * @Title: TestEntity
 * @ProjectName poi-demo
 * @Description: TODO
 * @since 2020/10/28 21:33
 */
public class TestEntity {
    /**
     * 编号
     */
    private Integer code;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private String sex;

    public TestEntity() {
    }

    public TestEntity(Integer code, String name, String sex) {
        this.code = code;
        this.name = name;
        this.sex = sex;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
