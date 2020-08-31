package com.josen.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * @ClassName AnnotationAopContext
 * @Description 使用注解的方式配置AOP
 * @Author Josen
 * @Create 2020/8/27 12:20
 */
@Configuration
@ComponentScan("com.josen")
@PropertySource("classpath:jdbc.properties")
@EnableAspectJAutoProxy
public class AnnotationAopContext {
    // 读取properties配置文件
    @Value("${jdbc.driver}")
    private String driver;
    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;
    // 获取dbutils runner对象
    @Bean
    public QueryRunner runner(DataSource dataSource){
        return new QueryRunner(dataSource);
    }

    // 配置数据库连接
    @Bean
    public DataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(driver);
        dataSource.setJdbcUrl(url);
        dataSource.setUser(username);
        dataSource.setPassword(password);
        return dataSource;
    }
}
