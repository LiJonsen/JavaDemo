package JDBC_test.JunitForDao;

import JDBC_test.Utils.DAO_JDBC_Utils;
import JDBC_test.bean.Customer;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 测试Druid数据库连接池
 */
public class TestDruid {
    private QueryRunner runner = new QueryRunner();
    // 向customers数据表插入一条数据
    @Test
    public void testing(){
        Connection conn = null;
        try {
            conn = DAO_JDBC_Utils.getDruidConn();
            conn.setAutoCommit(false);
            String sql = "insert into customers(name,email,birth) values(?,?,?)";
            ScalarHandler scalar = new ScalarHandler();
            Object insert = runner.insert(conn, sql, scalar,"喜茶","XiCha@gmail.com","2012-12-04");

            System.out.println(insert);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DAO_JDBC_Utils.closeConn(conn);
        }
    }

    @Test
    public void testing2(){
        Connection conn = null;
        try {
            conn = DAO_JDBC_Utils.getDruidConn();
            conn.setAutoCommit(false);
            String sql = "select name,email,birth from customers";
            BeanListHandler<Customer> beanListHandler = new BeanListHandler<>(Customer.class);
            List<Customer> query = runner.query(conn, sql, beanListHandler);
            query.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DAO_JDBC_Utils.closeConn(conn);
        }
    }

}
