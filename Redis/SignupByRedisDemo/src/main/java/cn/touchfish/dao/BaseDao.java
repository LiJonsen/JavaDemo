package cn.touchfish.dao;

import cn.touchfish.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName BaseDao
 * @Description
 * @Author Josen
 * @Create 2020/8/11 12:16
 */
public abstract class BaseDao<T> {
    private QueryRunner runner = new QueryRunner();
    private Class<T> clazz = null;
    // 获取当前泛型类型
    {
        Type gsc = getClass().getGenericSuperclass();
        if(gsc instanceof  Class){
            throw new RuntimeException("Missing type parameter");
        }else{
            ParameterizedType parameterized = (ParameterizedType)gsc;
            clazz = (Class<T>) parameterized.getActualTypeArguments()[0];
        }
//        System.out.println("当前BaseDao泛型类型："+gsc);
    }
    /**
     * 更新、插入、删除操作
     * @param sql
     * @return
     * @throws SQLException
     */
    public int update(String sql,Object ...params) throws SQLException {
        Connection conn = null;
        try {
            conn = JdbcUtils.getConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            int update = runner.update(conn, sql, params);
            JdbcUtils.closeConn(conn);
            return update;
        }

    }

    /**
     * 查询多条记录
     * @param sql
     * @return
     * @throws SQLException
     */
    public List<T> queryList(String sql) throws SQLException {
        Connection conn = null;
        try {
            conn = JdbcUtils.getConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            BeanListHandler<T> listHandler = new BeanListHandler<>(clazz);
            List<T> list = runner.query(conn, sql, listHandler);
            JdbcUtils.closeConn(conn);
            return list;
        }

    }

    /**
     * 查询单条记录
     * @param sql
     * @param params
     * @return
     * @throws SQLException
     */
    public T queryOne(String sql,Object ...params) throws SQLException {
        Connection conn = null;
        try {
            conn = JdbcUtils.getConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            BeanHandler<T> handler = new BeanHandler<>(clazz);
            T bean = runner.query(conn, sql, handler, params);
            JdbcUtils.closeConn(conn);
            return bean;
        }
    }

    /**
     * 执行函数查询-如：Count(*)
     * Tip：用于批量执行查询统计数据
     */
    public <E> E scalarQuery(String sql,Object ...params) throws SQLException {
        Connection conn = null;
        try {
            conn = JdbcUtils.getConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ScalarHandler<E> handler = new ScalarHandler<>();
            E query = runner.query(conn, sql, handler, params);
            JdbcUtils.closeConn(conn);
            return query;
        }
    }



}
