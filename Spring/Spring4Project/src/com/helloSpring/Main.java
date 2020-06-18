package com.helloSpring;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ClassName Main
 * @Description 测试Spring配置
 * @Author Josen
 * @Date 2020/6/17 16:53
 * @Version 1.0
 **/
public class Main {
    public static void main(String[] args) {
        //1. 创建 Spring 的 IOC 容器
        // 1.1 configLocation相对路径默认在src下
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
        //2. 从 IOC 容器中获取 bean 的实例
        HelloSpring bean = (HelloSpring)ctx.getBean("helloSpring");

        //3. 使用 bean
        System.out.println(bean);


        // 获取Customer bean实例
        Customer customer = (Customer) ctx.getBean("customer");
        System.out.println(customer);

        // 获取customerCollection实例，测试集合bean配置
        Customer customerCollection = (Customer) ctx.getBean("customerCollection");
        System.out.println(customerCollection);
    }
}
