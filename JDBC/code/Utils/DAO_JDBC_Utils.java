package JDBC_test.Utils;
import JDBC_test.connectTest;
import com.alibaba.druid.pool.DruidDataSourceFactory;


import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
// 升级版JDBC工具类（考虑事务处理）
public class DAO_JDBC_Utils {

    // 加载Druid数据库连接池
    public static DataSource ds = null;
    static{
        Properties pro = new Properties();
        try {
            pro.load(ClassLoader.getSystemClassLoader().getResourceAsStream("JDBC_test/druid.properties"));
            ds = DruidDataSourceFactory.createDataSource(pro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @Description 获取Druid数据库连接
     * @return
     */
    public static Connection getDruidConn() throws SQLException {
        Connection conn = ds.getConnection();
        return conn;
    }
    /**
     * @Description 获取数据库连接
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
     * @Description 关闭连接资源
     */
    public static void closeConnections(Statement ps){

        if(ps!=null){
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * @Description 关闭连接资源重载-查询
     */
    public static void closeConnections(Statement ps, ResultSet resultSet){
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
    public static void closeConn(Connection conn){
        if(conn!=null){
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void rollback(Connection conn){
        if(conn!=null){
            try {
                conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }


        }
    }
}
