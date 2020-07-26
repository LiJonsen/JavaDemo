package com.beans;

/**
 * @ClassName Employee
 * @Description Details
 * @Author Josen
 * @Create 12:37 12:37
 */
public class Employee {
    private String name;
    private int age;
    private Car car;
    public Employee() {
        System.out.println("2.1======>调用构造器");
    }

    public Employee(String name, int age, Car car) {
        this.name = name;
        this.age = age;
        this.car = car;
    }

    // bean初始化时调用
    public void init(){
        System.out.println("3.======>bean init初始化");
    }
    // bean销毁时调用
    public void destroy(){
        System.out.println("6.======>销毁IOC容器");
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", car=" + car +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
