package com.review.Proxy;

/**
 * @ClassName SuperMan
 * @Description 被代理类
 * @Author Josen
 * @Date 2020/6/23 19:52
 * @Version 1.0
 **/


public class SuperMan implements Human {
    public void show(){
        System.out.println("调用Human类中的show()");
    }
    public void eat(String food){
        System.out.println("eat "+food+" ......");
    }
}

