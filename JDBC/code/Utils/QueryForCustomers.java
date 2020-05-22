package JDBC_test.Utils;

import JDBC_test.bean.Customer;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

/**
 * 封装查询Customers数据表操作
 */
public class QueryForCustomers {
    public static Customer selectCustomersResult(String sql,Object ...args){
        Connection conn = null;
        java.sql.PreparedStatement ps = null;
        ResultSet resultSet = null;
        try{
            // 1. 获取连接
            conn = JDBC_Utils.getConnection();
            // 2. 预编译SQL语句
            ps = conn.prepareStatement(sql);

            // 2.1 填充占位符（?）
            for(int i=0;i<args.length;i++){
                ps.setObject(i+1,args[i]);
            }

            // 3. 执行SQL语句,获取结果集
            resultSet = ps.executeQuery();
            // 3.1 获取结果集的元数据
            ResultSetMetaData metaData = resultSet.getMetaData();

            // 获取列名个数
            int columnCount = metaData.getColumnCount();

            // 3.2 next()方法判断是否还有下一条记录
            if(resultSet.next()){
                Customer customer = new Customer();
                Class clazz = customer.getClass();

                // 3.3 通过反射动态的设置Customer属性
                for(int i=0;i<columnCount;i++){
                    String columnLabel = metaData.getColumnLabel(i+1);
                    Field declaredField = clazz.getDeclaredField(columnLabel);
                    declaredField.setAccessible(true);
                    declaredField.set(customer,resultSet.getObject(i+1));
                }
                return customer;
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            // 4. 关闭资源、连接
            JDBC_Utils.closeConnections(conn,ps,resultSet);
        }

        return null;
    }
}
