package cn.touchfish.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

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
    private static ComboPooledDataSource dataSource = MyC3p0Pool.getDataSource();

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
