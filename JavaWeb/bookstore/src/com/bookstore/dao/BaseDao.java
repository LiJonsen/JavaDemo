package com.bookstore.dao;
import com.bookstore.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.util.List;

/**
 * @ClassName BaseDao
 * @Description 通用数据表操作-增删改查
 * @Author Josen
 * @Date 2020/6/5 11:39
 * @Version 1.0
 **/
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
        System.out.println("当前BaseDao泛型类型："+gsc);
    }

    /**
     * 更新数据表-增删改操作
     * @param sql
     * @param params
     * @return 返回数据表更新条数
     */
    public int update(String sql,Object ...params){
        try {
            return runner.update(JdbcUtils.getDruidConn(),sql,params);
        } catch (Exception e) {
            e.printStackTrace();
            // 抛出错误，让Filter捕获
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询单个java对象（一行多列）
     * @param sql
     * @return 返回一个Java bean对象
     */
    public T queryForBean(String sql,Object ...params){
        try {
            BeanHandler<T> beanHandler = new BeanHandler<>(clazz);
            return runner.query(JdbcUtils.getDruidConn(),sql,beanHandler,params);
        } catch (Exception e) {
            e.printStackTrace();
            // 抛出错误，让Filter捕获
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询多条数据表记录
     * @param sql
     * @param params
     * @return 返回List集合
     */
    public List<T> queryList(String sql,Object ...params) {
        try {
            BeanListHandler<T> beanListHandler = new BeanListHandler<>(clazz);
            return runner.query(JdbcUtils.getDruidConn(), sql, beanListHandler, params);
        } catch (Exception e) {
            e.printStackTrace();
            // 抛出错误，让Filter捕获
            throw new RuntimeException(e);
        }
    }

    /**
     * 标量查询（如：count(*)、max(val)）
     * @param sql
     * @param params
     * @return 返回函数获取值
     */
    public Long scalarQuery(String sql,Object ...params) {
        try {

            ScalarHandler<Long> scalarHandler = new ScalarHandler<>();
            return runner.query(JdbcUtils.getDruidConn(), sql, scalarHandler, params);
        } catch (Exception e) {
            e.printStackTrace();
            // 抛出错误，让Filter捕获
            throw new RuntimeException(e);
        }
    }


}
