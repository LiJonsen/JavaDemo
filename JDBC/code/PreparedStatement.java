package JDBC_test;

import JDBC_test.Utils.CommonUtil;
import JDBC_test.Utils.JDBC_Utils;
import JDBC_test.Utils.QueryForCustomers;
import JDBC_test.bean.Customer;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * 使用PreparedStatement替换Statement，实现对数据表的增删改查
 * ORM编程思想：在Java中的一个类，对应数据表中的一条记录。类中的一个属性，对应数据表中的一个字段；
 * 1. 创建一个通用Utils，封装PreparedStatement对数据表的增删改操作，实现一个通用的方法；
 * 2. 创建一个CustomerForQuery，封装对数据单个表的查询操作，查询一条记录【表的一条记录看做一个Customer对象】；
 * 3. 创建一个OrderForQuery，封装对数据单个表的查询操作，查询一条记录
 *   【表的一条记录看做一个Order对象，并且处理列名与Order类属性不一致的问题（Tip：sql语句使用别名处理）】；
 *
 *
 * 方法名：
 * 1. getMetaDate()：获取结果集的元数据
 * 2. getMetaDate().getColumnCount()：获取结果集列数
 * 3. getMetaDate().getColumnName()：获取当前列的列名-不推荐使用
 * 4. getMetaDate().getColumnLabel()：获取当前列的别名，没有设置别名返回列名
 */
public class PreparedStatement {
    // 实现对数据表的增删改操作
    @Test
    public void testing(){
        Connection conn = null;
        java.sql.PreparedStatement ps = null;
        try{
            // 1. 获取连接
            conn = JDBC_Utils.getConnection();
            // 2. 预编译SQL语句
            String sql = "insert into customers(id,name,email,birth) values(?,?,?,?)";
            ps = conn.prepareStatement(sql);

            // 2.1 填充占位符（?）
            ps.setObject(1,19);
            ps.setObject(2,"周星驰");
            ps.setObject(3,"zhouxx@gmail.com");

            long dateMillis = CommonUtil.getDateMillis("1980-6-22");
            ps.setObject(4,new java.sql.Date(dateMillis));
            // 3. 执行SQL语句
            ps.execute();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            // 4. 关闭资源、连接
            JDBC_Utils.closeConnections(conn,ps);
        }

    }

    /**
     * 测试封装数据表增删改操作
     */
    @Test
    public void testUpdateSqlTable(){
        // 删除操作
//        String delete_sql = "delete from customers where name=?";
//        boolean status = JDBC_Utils.updateSqlTable(delete_sql, "汤唯");
//        System.out.println(status?"删除成功":"删除失败");

        // 更新操作
        String update_sql = "update customers set name=? where id=?";
        boolean status2 = JDBC_Utils.updateSqlTable(update_sql, "张国荣","19");
        System.out.println(status2?"修改成功":"修改失败");

    }

    /**
     * 测试查询单个表操作
     * 难点：根据不同个数的列名查询，数据表一条数据对应一个Customer类（涉及反射知识）
     * 注：与增删改不同，查询有结果集返回。
     */
    @Test
    public void testSelectTable(){
        Connection conn = null;
        java.sql.PreparedStatement ps = null;
        ResultSet resultSet = null;
        try{
            // 1. 获取连接
            conn = JDBC_Utils.getConnection();
            // 2. 预编译SQL语句
            String sql = "select id,`name`,email,birth from customers where id=?";
            ps = conn.prepareStatement(sql);

            // 2.1 填充占位符（?）
            ps.setObject(1,19);

            // 3. 执行SQL语句,获取结果集
            resultSet = ps.executeQuery();
            // 3.1 next()方法判断是否还有下一条记录
            if(resultSet.next()){
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String email = resultSet.getString(3);
                java.sql.Date birth = resultSet.getDate(4);
                Customer customer = new Customer(id, name, email, birth);
                System.out.println(customer);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            // 4. 关闭资源、连接
            JDBC_Utils.closeConnections(conn,ps,resultSet);
        }
    }

    /**
     * 测试查询Customer数据表方法
     */
    @Test
    public void testQueryForCustomers(){
        String sql = "select id,name,email,birth from customers where name=?";
        Customer customer = QueryForCustomers.selectCustomersResult(sql, "张学友");
        System.out.println(customer);
    }
}
