package com.testing;

import com.beans.Car;
import com.beans.RichMan;
import com.utils.CommonUtils;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

/**
 * @ClassName BeanSetting
 * @Description Details
 * @Author Josen
 * @Create 9:39 9:39
 */
public class BeanSetting {
    @Test
    public void testExtend(){
        ApplicationContext context = CommonUtils.getApplicationContext("BeanSetting.xml");

        Car car1 = context.getBean("car1", Car.class);
        System.out.println(car1);
        RichMan man1 = context.getBean("man1", RichMan.class);
        System.out.println(man1);

    }
}
