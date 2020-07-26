package com.testing;

import com.beans.Car;
import com.beans.Employee;
import com.beans.RichMan;
import com.utils.CommonUtils;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ClassName TestLifeCycle
 * @Description 测试bean的生命周期
 * @Author Josen
 * @Create 12:34 12:34
 */
public class TestLifeCycle {
    @Test
    public void testing(){
        System.out.println("1.======>加载IOC容器XML文件");
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("TestLifeCycle.xml");

        // 获取bean对象
        System.out.println("4.======>调用getBean方法获取bean对象");
        Employee employee = context.getBean("employee", Employee.class);

        // 使用bean对象
        System.out.println("5======>使用bean对象"+employee);
        // 销毁IOC容器
        context.close();
    }
    // 测试自动装配
    @Test
    public void testAutowire(){
        ApplicationContext context = CommonUtils.getApplicationContext("TestLifeCycle.xml");
        Employee employee = context.getBean("emp2", Employee.class);
        System.out.println(employee);
    }
}
