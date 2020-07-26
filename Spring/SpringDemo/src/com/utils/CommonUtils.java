package com.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ClassName CommonUtils
 * @Description Details
 * @Author Josen
 * @Create 9:13 9:13
 */
public final class CommonUtils {
    public static ApplicationContext getApplicationContext(String filename){
        return new ClassPathXmlApplicationContext(filename);
    }
}
