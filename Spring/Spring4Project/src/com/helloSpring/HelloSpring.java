package com.helloSpring;

/**
 * @ClassName HelloSpring
 * @Description TODO
 * @Author Josen
 * @Date 2020/6/17 16:49
 * @Version 1.0
 **/
public class HelloSpring {
    private String name;
    private int id;
    private int age;
    public HelloSpring() {
    }

    public HelloSpring(String name, int id, int age) {
        this.name = name;
        this.id = id;
        this.age = age;
    }

    @Override
    public String toString() {
        return "HelloSpring{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", age=" + age +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
