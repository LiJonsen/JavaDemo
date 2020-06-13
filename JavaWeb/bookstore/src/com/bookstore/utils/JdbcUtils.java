package com.bookstore.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
/**
 * @ClassName JdbcUtils
 * @Description JDBC工具类
 * @Author Josen
 * @Date 2020/6/5 12:52
 * @Version 1.0
 **/
public class JdbcUtils {
    // 使用ThreadLocal存储当前线程数据库连接资源（管理事务）
    private static ThreadLocal<Connection> conn = new ThreadLocal<>();
    // 加载Druid数据库连接池
    public static DataSource ds = null;
    static{
        Properties pro = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream(JdbcUtils.class.getClassLoader().getResource("druid.properties").getPath());

//            InputStream ras = ClassLoader.getSystemClassLoader().getResourceAsStream("druid.properties");
            System.out.println("fileInputStream："+fileInputStream);
            pro.load(fileInputStream);
            ds = DruidDataSourceFactory.createDataSource(pro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @Description 获取Druid数据库连接
     * @return
     */
    public static Connection getDruidConn(){
        // 默认从ThreadLocal获取
        Connection connection = conn.get();
        if(connection==null){
            try {
                // 获取连接资源
                connection = ds.getConnection();
                // 设置手动提交sql
                connection.setAutoCommit(false);
                conn.set(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    /**
     * 手提提交sql并释放连接资源
     */
    public static void commitAndCloseConnection(){
        Connection connection = conn.get();
        if(connection!=null){
            try {
                System.out.println("************run commitAndCloseConnection****************");
                connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
//      执行 remove 操作，否则就会出错。（因为 Tomcat 服务器底层使用了线程池技术）
        conn.remove();
    }

    /**
     * 事务回滚并关闭连接资源
     */
    public static void rollbackAndCloseConnection(){
        Connection connection = conn.get();
        if(connection!=null){
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        //执行 remove 操作，否则就会出错。（因为 Tomcat 服务器底层使用了线程池技术）
        conn.remove();
    }

    /**
     * 释放连接资源
     * @param conn
     */
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
