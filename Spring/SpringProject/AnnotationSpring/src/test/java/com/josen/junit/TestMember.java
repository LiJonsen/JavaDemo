package com.josen.junit;

import com.josen.config.ApplicationConfig;
import com.josen.controller.MemberController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @ClassName TestMember
 * @Description Details
 * @Author Josen
 * @Create 2020/8/26 21:05
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class TestMember {
    @Autowired
    private MemberController memberController;
    @Test
    public void testMemberList(){
        memberController.echoMemberList();
    }
}
