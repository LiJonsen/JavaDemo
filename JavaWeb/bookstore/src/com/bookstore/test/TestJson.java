package com.bookstore.test;

import com.bookstore.pojo.Order;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName TestJson
 * @Description java中对json数据进行转换
 * @Author Josen
 * @Date 2020/6/13 17:30
 * @Version 1.0
 **/
public class TestJson {
    private static Gson gson = new Gson();

    @Test
    public void testBean(){
        Order order = new Order("1234",new Date(),0,1);
        // 1. JavaBean对象转json
        String s = gson.toJson(order);
        System.out.println("Json数据："+s);

        // 2. json转bean
        Order order1 = gson.fromJson(s, Order.class);
        System.out.println("bean对象:"+order1);
    }
    @Test
    public void testList(){
        List<Order> orders = new ArrayList<>();
        orders.add(new Order("111",new Date(),0,1));
        orders.add(new Order("222",new Date(),1,2));
        orders.add(new Order("333",new Date(),2,3));

        // 1. List转json
        String s = gson.toJson(orders);
        System.out.println("List转json："+s);

        // 2. json转List
        // new TypeToken<List<Order>>(){}.getType() - 获取转换对应的类型
        List<Order> list = gson.fromJson(s, new TypeToken<List<Order>>() {
        }.getType());

        System.out.print("json转List：");
        System.out.println(list);
    }
    @Test
    public void testMap(){
        HashMap<Integer, Order> orders = new HashMap<>();
        orders.put(0,new Order("111",new Date(),0,1));
        orders.put(1,new Order("222",new Date(),0,2));
        orders.put(2,new Order("333",new Date(),0,3));

        String s = gson.toJson(orders);

        System.out.println("Map转Json："+s);

        HashMap<Integer,Order> vals = gson.fromJson(s,new TypeToken<HashMap<Integer,Order>>(){}.getType());
        System.out.println("Json转Map："+vals);
    }
}
