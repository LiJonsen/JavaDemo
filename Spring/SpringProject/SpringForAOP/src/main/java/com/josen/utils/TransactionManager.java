package com.josen.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @ClassName TransactionManager
 * @Description 事务管理（使用AOP切面）
 * @Author Josen
 * @Create 2020/8/27 13:54
 */
@Component
public class TransactionManager {
    // 用于存储当前线程的数据库连接
    private ThreadLocal<Connection> threadLocal = new ThreadLocal<>();
    @Autowired
    private DataSource dataSource;

    /**
     * 开启事务
     */
    public void CreateCurrentThreadConn() throws SQLException {
        System.out.println("开启事务...");
        Connection connection = dataSource.getConnection();
        // 设置手动提交事务
        connection.setAutoCommit(false);
        // 将连接存储到当前线程
        threadLocal.set(connection);
    }


    /**
     * 获取当前线程连接
     */
    public Connection getCurrentConn(){
        System.out.println("获取当前线程...");

        return threadLocal.get();
    }

    /**
     * 提交当前事务并关闭连接
     */
    public void commitAndClose(){
        System.out.println("提交并关闭连接...");
        Connection conn = threadLocal.get();
        if(conn!=null){
            try {
                conn.commit();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        threadLocal.remove();
    }


    /**
     * 回滚当前事务并关闭连接
     */
    public void rollbackAndClose(){
        System.out.println("回滚并关闭连接...");

        Connection conn = threadLocal.get();

        if(conn!=null){
            try {
                conn.rollback();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        threadLocal.remove();
    }
}
