package com.josen;

import com.josen.beans.Person;
import com.josen.config.ConfigForSelector;
import com.josen.controller.MemberController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @ClassName TestImportSelector
 * @Description Details
 * @Author Josen
 * @Create 2020/8/27 9:02
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ConfigForSelector.class)
public class TestImportSelector {

    @Test
    public void testing(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConfigForSelector.class);
        MemberController bean = context.getBean(MemberController.class);
        bean.echoMemberList();
    }
}
