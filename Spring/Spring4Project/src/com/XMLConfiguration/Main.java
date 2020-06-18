package com.XMLConfiguration;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @ClassName Main
 * @Description TODO
 * @Author Josen
 * @Date 2020/6/17 18:54
 * @Version 1.0
 **/
public class Main {
    public static void main(String[] args) {
//        Connection conn = JdbcUtils.getDruidConnection();
        System.out.println(Season.WINTER);
        System.out.println(Season.SUMMER);
    }
}

enum Season{
    SUMMER("夏天","Hot"),
    WINTER("冬天","Cold");
    private String name;
    private String desc;
    Season(String name,String desc){
        this.name = name;
        this.desc = desc;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getName(){
        return name;
    }
    public String getDesc(){
        return desc;
    }
}
