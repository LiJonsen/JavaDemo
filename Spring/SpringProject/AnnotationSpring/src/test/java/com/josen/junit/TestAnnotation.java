package com.josen.junit;

import com.josen.beans.Person;
import com.josen.config.ApplicationConfig;
import org.apache.commons.dbutils.QueryRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;

/**
 * @ClassName TestAnnotation
 * @Description
 * @Author Josen
 * @Create 2020/8/26 20:16
 */
// Spring整合Junit单元测试
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class TestAnnotation {

//    private AnnotationConfigApplicationContext context;
    @Autowired
    private Person person;

    @Autowired
    private DataSource dataSource;
    @Autowired
    private QueryRunner runner;

//    @Before
//    public void init(){
//        context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
//    }
    @Test
    public void initBeans(){
//        Person bean = context.getBean(Person.class);
        person.setName("josen");
        person.setAge(23);
        System.out.println(person);
    }

    @Test
    public void testDataSource(){
        System.out.println(dataSource);
        System.out.println(runner);
    }
}
