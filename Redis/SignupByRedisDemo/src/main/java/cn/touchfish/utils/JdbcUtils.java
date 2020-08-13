package cn.touchfish.utils;

import cn.touchfish.junit.TestJdbc;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @ClassName JdbcUtils
 * @Description JDBC工具类
 * @Author Josen
 * @Create 2020/8/10 21:33
 */
public final class JdbcUtils {
    private static ComboPooledDataSource dataSource = null;
    static {
        dataSource = new ComboPooledDataSource();
        // 配置c3p0连接池
        Properties properties = new Properties();
        InputStream stream = null;
        try {
            // getResource - 每次都是获取最新的文件
            stream = TestJdbc.class.getClassLoader().getResource("jdbc.properties").openStream();
            // getResourceAsStream - 优先在缓存中读取文件
//        InputStream stream = JdbcUtils.class.getResourceAsStream("jdbc.properties");
            properties.load(stream);
            dataSource.setMaxPoolSize(10);
            dataSource.setMinPoolSize(3);
            dataSource.setDriverClass(properties.getProperty("driverClass"));
            dataSource.setJdbcUrl(properties.getProperty("dev_url"));
            dataSource.setUser(properties.getProperty("dev_username"));
            dataSource.setPassword(properties.getProperty("dev_password"));
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(stream!=null){
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(properties != null){
                properties.clear();
            }
        }
    }
    public static Connection getConn() throws SQLException {
       return dataSource.getConnection();
    }
    public static void closeConn(Connection conn){
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
