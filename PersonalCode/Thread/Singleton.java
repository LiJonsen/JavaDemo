package com.review.Thread;

/**
 * @ClassName Singleton
 * @Description
 * 单例模式：某个类只能存在一个对象实例
 * 懒汉式：当调用getInstance方法，发现instance==null时才创建实例
 * 特点：延迟对象的创建
 * 饿汉式：类加载时已经将instance赋值了
 * 特点：初始化便要加载对象
 * @Author Josen
 * @Date 2020/6/24 10:55
 * @Version 1.0
 **/
public class Singleton {
    // 懒汉式
    private static Singleton lh_instance = null;
    // 饿汉式
    private static Singleton eh_instance = new Singleton();
    private Singleton(){}
    // 获取懒汉式实例
    private static Singleton get_lh_instance(){
        if(lh_instance == null){
            synchronized(Singleton.class){
                if(lh_instance==null){
                    lh_instance = new Singleton();
                }
            }
        }
        return lh_instance;
    }
    // 获取饿汉式实例
    private static Singleton get_eh_instance(){
        return eh_instance;
    }
}
