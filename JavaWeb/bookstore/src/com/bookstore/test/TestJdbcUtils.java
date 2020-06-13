package com.bookstore.test;
import com.bookstore.utils.JdbcUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @ClassName TestJdbcUtils
 * @Description 测试JDBC工具类
 * @Author Josen
 * @Date 2020/6/4 20:31
 * @Version 1.0
 **/
public class TestJdbcUtils {
    @Test
    public void testing(){
        try {
            Connection connection = JdbcUtils.getDruidConn();
            System.out.println(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
