package com.XMLConfiguration;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @ClassName JdbcUtils
 * @Description TODO
 * @Author Josen
 * @Date 2020/6/17 18:40
 * @Version 1.0
 **/
public class JdbcUtils {
    // 获取数据库连接
    public static Connection getDruidConnection(){
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("jdbcConfiguration.xml");
        DruidDataSource dataSource = (DruidDataSource)ctx.getBean("dataSource");
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
