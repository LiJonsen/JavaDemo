package com.beans;

/**
 * @ClassName JsonData
 * @Description Details
 * @Author Josen
 * @Create 2020/7/30 21:12
 */
public class JsonData {
    private String name;
    private Integer age;

    public JsonData() {
    }

    public JsonData(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "JsonData{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
