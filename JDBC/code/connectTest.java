package JDBC_test;

import org.junit.Test;

import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class connectTest {
    // 使用JDBC连接数据库
    @Test
    public void testConnect() throws Exception {
        // 1. 配置信息
        String url = "jdbc:mysql://localhost:3306/test";
        String user = "root";
        String password = "123456";
        // 2. 注册驱动
        // 方式一：
//        Class clazz = Class.forName("com.mysql.cj.jdbc.Driver");
//        Driver o = (Driver)clazz.newInstance();
//        DriverManager.registerDriver(o);
//
        // 方式二：在加载com.mysql.cj.jdbc.Driver类时，执行了一块注册驱动的静态代码；
        Class.forName("com.mysql.cj.jdbc.Driver");
        // 3. 获取连接
        Connection conn =  DriverManager.getConnection(url,user,password);
        System.out.println(conn);
    }

    // 读取jdbc.properties文件，获取配置信息
    @Test
    public void testFileConnect() throws Exception{
               // 获取配置文件信息
        InputStream resource = connectTest.class.getClassLoader().getResourceAsStream("JDBC_test/jdbc.properties");
        Properties properties = new Properties();
        properties.load(resource);
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");

        // 注册驱动+建立连接
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, user, password);

        System.out.println(connection);
    }
}
