package com.atguigu.ReflectionTest;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 反射示例01
 * 对比正常方式与反射方式使用类的成员变量和方法
 */
public class SimpleDemo {
    // 正常方式
    @Test
    public void testNormal(){
        Person p = new Person("Josen",23);

        p.setAge(25);

        p.show();

        System.out.println(p);
    }
    // 反射方式
    @Test
    public void testReflection() throws Exception {
        // 1. 获取Person的class
        Class clazz = Person.class;
        // clazz = class com.atguigu.ReflectionTest.Person
        System.out.println(clazz);
        // 2. 获取Person的构造器
        Constructor cons = clazz.getDeclaredConstructor(String.class,int.class);
        // 2.1 调用构造器(默认返回Object类型，强转为Person类型)
        Object obj = cons.newInstance("Tom",20);
        Person person = (Person) obj;
        System.out.println(person.toString());
        // 3.1 通过反射调用Person属性
        Field money = clazz.getDeclaredField("money");
        money.set(person,99.123);
        System.out.println(person.toString());

        // 3.2 通过反射调用Person方法
        Method show = clazz.getDeclaredMethod("show");
        show.invoke(person);

        System.out.println("----------------调用私有结构-------------------");
        // 4 调用Person类的私有结构（关键方法setAccessible）
        // 4.1 调用私有构造器
        Constructor private_cons = clazz.getDeclaredConstructor(String.class);
        private_cons.setAccessible(true);
        Person p_person = (Person) private_cons.newInstance("Mary");
        System.out.println("Person实例对象："+p_person);

        // 4.2 调用私有属性
        Field private_age = clazz.getDeclaredField("age");
        private_age.setAccessible(true);
        private_age.set(p_person,79);
        System.out.println("修改Person实例对象私有属性："+p_person);
        // 4.3 调用私有方法
        Method getDetail = clazz.getDeclaredMethod("getDetail");
        getDetail.setAccessible(true);
        // 获取getDetail方法返回值

        String detail = (String) getDetail.invoke(p_person);
        System.out.println(detail);
    }
}

class Person{
    private String name;
    private int age;
    public Double money=0.0;
    public Person(){
    }
    private Person(String name){
        this.name = name;
    }
    public Person(String name,int age){
        this.name = name;
        this.age = age;
    }
    private String getDetail(){
        System.out.println("调用Person实例对象私有方法");
        String detail = "我是getDetail私有方法返回值detail内容";
        return detail;
    }
    public void show(){
        System.out.println("这是Person的show方法");
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

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", money=" + money +
                '}';
    }
}
