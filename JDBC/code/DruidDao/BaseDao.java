package JDBC_test.DruidDao;

import JDBC_test.Utils.DAO_JDBC_Utils;
import JDBC_test.bean.Customer;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * 使用Druid线程池实现DAO
 */
public abstract class BaseDao<T> {
    // DBUtils工具类实例
    private QueryRunner runner = new QueryRunner();

    // 当前类泛型Class
    private Class<T> clazz = null;
    /**
     * 获取当前类的泛型
     */
    {
        // 获取当前T泛型的Class
        Type superclass = getClass().getGenericSuperclass();
        if (superclass instanceof Class) {
            throw new RuntimeException("Missing type parameter.");
        } else {
            ParameterizedType parameterized = (ParameterizedType)superclass;
            clazz = (Class<T>) parameterized.getActualTypeArguments()[0];
        }
        System.out.println("当前运行时类："+clazz);
    }

    // 新增、删除、修改操作
    public int update(Connection conn,String sql,Object ...params) throws SQLException {
        return runner.update(conn, sql, params);
    }

    /**
     * 标量查询操作-查询单个java对象
      * @param conn
     * @param sql
     * @param params
     * @return
     * @throws SQLException
     */
    public T scalarQuery(Connection conn,String sql,Object ...params) throws SQLException {
        // 执行查询
        BeanHandler<T> beanHandler = new BeanHandler<>(clazz);
        return runner.query(conn, sql, beanHandler, params);
    }

    /**
     * 查询类似Count(*)、Max(date)数值
     * @param conn
     * @param sql
     * @param params
     * @return
     */
    public Long queryValue(Connection conn,String sql,Object ...params) throws SQLException {
        ScalarHandler<Long> scalarHandler = new ScalarHandler<>();
        return runner.query(conn, sql, scalarHandler, params);
    }

    /**
     * 批量查询操作
     * @param conn
     * @param sql
     * @param params
     * @return
     * @throws SQLException
     */
    public List<T> query(Connection conn,String sql,Object ...params) throws SQLException {
        BeanListHandler<T> beanListHandler = new BeanListHandler<>(clazz);
        List<T> query = runner.query(conn, sql, beanListHandler, params);
        return query;
    }



}
