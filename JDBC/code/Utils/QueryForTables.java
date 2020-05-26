package JDBC_test.Utils;

import JDBC_test.bean.Customer;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

/**
 * 查询多个数据表
 */
public class QueryForTables {
    public static <T> List<T> getResult(Class<T> clazz, String sql, Object ...args){
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

            // 声明集合，用于存储查询返回的结果集
            ArrayList<T> ts = new ArrayList<>();
            // 3.2 next()方法判断是否还有下一条记录
            while(resultSet.next()){
                T obj = clazz.newInstance();
                // 3.3 通过反射动态的设置Customer属性
                for(int i=0;i<columnCount;i++){
                    String columnLabel = metaData.getColumnLabel(i+1);
                    Field declaredField = clazz.getDeclaredField(columnLabel);
                    declaredField.setAccessible(true);
                    String columnTypeName = metaData.getColumnTypeName(i + 1);
                    // 处理Blob类型数据
                    if(columnTypeName.contains("BLOB")){
                        System.out.println(columnTypeName);
                        declaredField.set(obj,resultSet.getBlob(i+1));
                    }else{
                        declaredField.set(obj,resultSet.getObject(i+1));
                    }
                }
                ts.add(obj);
            }

            return ts;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            // 4. 关闭资源、连接
            JDBC_Utils.closeConnections(conn,ps,resultSet);
        }

        return null;
    }
}
