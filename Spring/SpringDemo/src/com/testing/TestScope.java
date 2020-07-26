package com.testing;

import com.beans.Car;
import com.utils.CommonUtils;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

/**
 * @ClassName TestScope
 * @Description Details
 * @Author Josen
 * @Create 12:25 12:25
 */
public class TestScope {
    @Test
    public void testing(){
        ApplicationContext context = CommonUtils.getApplicationContext("TestLifeCycle.xml");

        Car car = context.getBean("car", Car.class);
        System.out.println(car);
        Car c2 = context.getBean("car", Car.class);

        System.out.println(car==c2);

    }
}
