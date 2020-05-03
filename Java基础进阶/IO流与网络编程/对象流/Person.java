package com.atguigu.IO_Test;
import java.io.Serializable;

/**
 * 对象流需要保存的类
 */
public class Person implements Serializable {
    private static final long serialVersionUID = -684951232767710L;
    private String name;
    private int age;
    // 对应的Account类也需要实现Serializable
    // 否则报错：java.io.NotSerializableException
    private Account account;
    public Person(){}
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
        this.account = new Account(50000);
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", account=" + account +
                '}';
    }
}

class Account implements Serializable{
    private static final long serialVersionUID = -68411233267967710L;
    private int money;
    public Account(){}
    public Account(int money){
        this.money = money;
    }

    @Override
    public String toString() {
        return "Account{" +
                "money=" + money +
                '}';
    }
}
