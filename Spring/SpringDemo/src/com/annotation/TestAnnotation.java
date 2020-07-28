package com.annotation;

import com.annotation.controller.AddUser;
import com.utils.CommonUtils;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

/**
 * @ClassName TestAnnotation
 * @Description Details
 * @Author Josen
 * @Create 2020/7/27 8:49
 */
public class TestAnnotation {
    @Test
    public void testScanComponent(){
        ApplicationContext context = CommonUtils.getApplicationContext("SpringAnnotation.xml");
        AddUser user = context.getBean("addUser", AddUser.class);
        user.runAddUser();
    }
}
