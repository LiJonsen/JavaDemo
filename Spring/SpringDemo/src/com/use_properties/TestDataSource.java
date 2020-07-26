package com.use_properties;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.utils.CommonUtils;
import org.springframework.context.ApplicationContext;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @ClassName TestDataSource
 * @Description Details
 * @Author Josen
 * @Create 15:31 15:31
 */
public class TestDataSource {
    public static void main(String[] args) {
        ApplicationContext context = CommonUtils.getApplicationContext("ConfigC3p0.xml");
        ComboPooledDataSource dataSource = context.getBean("dataSource", ComboPooledDataSource.class);
        System.out.println(dataSource);
        try {
            Connection connection = dataSource.getConnection();
            System.out.println(connection);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
