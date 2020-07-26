package com.beans;


/**
 * @ClassName Person
 * @Description Details
 * @Author Josen
 * @Create 16:56 16:56
 */
public class Person {
    private String name;
    public Person() {
    }
    public Person(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
