package JDBC_test.Utils;

import JDBC_test.connectTest;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBC_Utils {
    /**
     * 获取数据库连接
     * @return 数据库连接实例对象
     */
    public static Connection getConnection() throws Exception{
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
            return connection;
    }
    /**
     * 关闭连接资源
     */
    public static void closeConnections(Connection conn, Statement ps){
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(ps!=null){
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 关闭连接资源重载-查询
     */
    public static void closeConnections(Connection conn, Statement ps, ResultSet resultSet){
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(ps!=null){
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(resultSet!=null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 封装对数据表的增删改操作
     * 难点：如何填充不同数量的占位符?
     * @return true:操作成功 false:操作失败
     */
    public static boolean updateSqlTable(String sql,Object ...args){
        boolean status = false;
        Connection conn = null;
        java.sql.PreparedStatement ps = null;
        try{
            // 1. 获取连接
            conn = JDBC_Utils.getConnection();
            // 2. 预编译SQL语句
            ps = conn.prepareStatement(sql);

            // 2.1 填充占位符（?）
            for(int i=0;i<args.length;i++){
                ps.setObject(i+1,args[i]);
            }
            // 3. 执行SQL语句
            ps.execute();

            status = true;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            // 4. 关闭资源、连接
            JDBC_Utils.closeConnections(conn,ps);
        }

        return status;
    }
}
