package com.atguigu.hashSetTest;
import org.junit.Test;
import java.util.*;

public class TestHashSet {
    /**
     * 练习：使用TreeSet实现comparable和comparator排序
     * comparable实现排序name从小到大
     */
    @Test
    public void testComparable(){
        TreeSet list = new TreeSet();
        Person p1 = new Person("LiuDeHua", 20, new MyDate(1986, 5, 12));
        Person p2 = new Person("GuoFuCheng", 33, new MyDate(1969, 3, 12));
        Person p3 = new Person("ZhangXueYou", 20, new MyDate(1993, 2, 3));
        Person p4 = new Person("XieTingFeng", 32, new MyDate(1986, 5, 9));
        Person p5 = new Person("ChenXiaoChun", 21, new MyDate(1997, 11, 22));

        list.add(p1);
        list.add(p2);
        list.add(p3);
        list.add(p4);
        list.add(p5);

        Iterator iterator = list.iterator();
        for(Object obj:list){
            System.out.println(obj);
        }
    }
    // comparator实现排序birthday从小到大
    @Test
    public void testComparator(){
        TreeSet list = new TreeSet(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                if(o1 instanceof Person && o2 instanceof Person){
                    Person p1 = (Person) o1;
                    Person p2 = (Person) o2;
                    return p1.getBirthday().compareTo(p2.getBirthday());
                }
                return 0;
            }
        });

        Person p1 = new Person("LiuDeHua", 20, new MyDate(1986, 5, 12));
        Person p2 = new Person("GuoFuCheng", 33, new MyDate(1969, 3, 12));
        Person p3 = new Person("ZhangXueYou", 20, new MyDate(1993, 2, 3));
        Person p4 = new Person("XieTingFeng", 32, new MyDate(1986, 5, 9));
        Person p5 = new Person("ChenXiaoChun", 21, new MyDate(1997, 11, 22));
        list.add(p1);
        list.add(p2);
        list.add(p3);
        list.add(p4);
        list.add(p5);

        Iterator iterator = list.iterator();
        for(Object obj:list){
            System.out.println(obj);
        }
    }
}
