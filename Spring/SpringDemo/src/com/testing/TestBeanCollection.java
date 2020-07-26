package com.testing;

import com.beans.RichMan;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ClassName TestBeanCollection
 * @Description Details
 * @Author Josen
 * @Create 8:40 8:40
 */
public class TestBeanCollection {
    private ApplicationContext context;
    @Before
    public void before(){
        context = new ClassPathXmlApplicationContext("TestBeanCollection.xml");
    }
    @Test
    public void testCollection(){
        RichMan richMan = context.getBean("richMan", RichMan.class);
        System.out.println(richMan);
        RichMan testMap = context.getBean("richMan2", RichMan.class);
        System.out.println(testMap);
    }
}
