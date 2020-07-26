package com.testing;

import com.beans.Car;
import com.utils.CommonUtils;
import org.springframework.context.ApplicationContext;

/**
 * @ClassName TestFactoryBbean
 * @Description 使用工厂bean
 * @Author Josen
 * @Create 9:11 9:11
 */
public class TestFactoryBean {
    public static void main(String[] args) {
        ApplicationContext context = CommonUtils.getApplicationContext("TestFactoryBean.xml");

        Car factoryBean = context.getBean("factoryBean", Car.class);
        System.out.println(factoryBean);
    }
}
