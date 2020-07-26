package com.testing;

import com.beans.Car;
import com.beans.Person;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ClassName QuickStart
 * @Description Details
 * @Author Josen
 * @Create 16:58 16:58
 */
public class QuickStart {

    /**
     * 使用IOC容器，获取xml配置好的实例对象
     */
    @Test
    public void testGetIOCBean(){
        // 加载IOC容器
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        // 方式一：需要强转
        Person person = (Person)context.getBean("person");
        System.out.println(person);

        // 方式二：如果出现多个同类型的bean，会出现错误
        // expected single matching bean but found 2: person,person2
//        Person p2 = context.getBean(Person.class);
//        System.out.println(p2);
        // 方式三：常用方式
        Person p3 = context.getBean("person2", Person.class);
        Person p4 = context.getBean("person", Person.class);
        System.out.println(p3);
        System.out.println(p4);
    }

    /**
     * DI依赖注入
     */
    @Test
    public void testDISetValue(){
        // 加载IOC容器
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("TestDIContext.xml");
        // 1.通过bean的setXxx()方法赋值
        Car car1 = context.getBean("setCar", Car.class);
        System.out.println(car1);

        // 2.通过构造器方式赋值
        Car car2 = context.getBean("consCar", Car.class);
        System.out.println(car2);


        // 3. 使用p命名空间方式赋值
        Car car3 = context.getBean("carByP", Car.class);
        System.out.println(car3);
    }
}
