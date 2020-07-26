package com.josen.getting_start;

import com.josen.getting_start.bean.Employee;
import com.josen.getting_start.mapper.EmployeeMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import utils.CommonUtils;

import java.io.IOException;
import java.util.List;

/**
 * @ClassName TestDynamicSql
 * @Description 测试动态SQL拼接
 * @Author Josen
 * @Create 11:14 11:14
 */
public class TestDynamicSql {
    private static SqlSession sqlSession=null;
    static {
        try {
            sqlSession = CommonUtils.createSqlSession();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testIfWhere(){
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Employee employee = new Employee();
            employee.setEid(100);
            employee.setFirstName("Steven");
            Employee emp = mapper.queryEmployeeForDynamicSql(employee);
            System.out.println(emp);

            if(sqlSession!=null)
                sqlSession.close();
    }
    @Test
    public void testForeach(){
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        int[] ids = new int[]{100,101,102};
        List<Employee> employees = mapper.queryEmployeesByForeach(ids);
        employees.forEach(System.out::println);
    }
}
