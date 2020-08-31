package com.josen;

import com.josen.beans.Account;
import com.josen.config.AnnotationAopContext;
import com.josen.config.ConfigForSelector;
import com.josen.dao.AccountDao;
import com.josen.service.AccountService;
import com.josen.utils.TransactionManager;
import org.apache.commons.dbutils.QueryRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @ClassName TestAop
 * @Description 测试使用AOP统一管理事务
 * @Author Josen
 * @Create 2020/8/27 13:49
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AnnotationAopContext.class)
public class TestAop {
//    @Autowired
//    private DataSource dataSource;
    // 测试转账Demo
    @Test
    public void testAnnotationAop() throws SQLException {
        AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(AnnotationAopContext.class);

        AccountService accountService = app.getBean(AccountService.class);

        Account tom = accountService.getAccountByUsername("Tom");
        Account jerry = accountService.getAccountByUsername("Jerry");
        accountService.transferBalance(jerry,tom,500.0);

    }
}
