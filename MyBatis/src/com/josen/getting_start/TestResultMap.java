package com.josen.getting_start;

import com.josen.getting_start.bean.Department;
import com.josen.getting_start.bean.Employee;
import com.josen.getting_start.mapper.DepartmentMapper;
import com.josen.getting_start.mapper.EmployeeMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import utils.CommonUtils;

import java.io.IOException;
import java.util.List;

/**
 * @ClassName TestResultMap
 * @Description 测试自定义映射
 * @Author Josen
 * @Create 21:04 21:04
 */
public class TestResultMap {
    /**
     * 级联查询测试
     */
    @Test
    public void testResultMap(){
        SqlSession sqlSession = null;
        try {
            sqlSession = CommonUtils.createSqlSession();
            DepartmentMapper mapper = sqlSession.getMapper(DepartmentMapper.class);
            Department department = mapper.queryDepartmentAndLocation(100);
            System.out.println(department);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(sqlSession!=null)
                sqlSession.close();
        }
    }

    /**
     * 分步查询测试
     */
    @Test
    public void testResultMapForStep(){
        SqlSession sqlSession = null;
        try {
            sqlSession = CommonUtils.createSqlSession();
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            // 查询salary>=15000的员工信息
            List<Employee> employees = mapper.queryEmployeeBySalaryForStep(15000.0);
            // 懒加载测试
            for (Employee employee : employees) {
                // 1.不使用部门信息，不会执行部门信息查询SQL
                System.out.printf("名字：%s \t 工资：%s \n",employee.getFirstName()+employee.getLastName(),employee.getSalary().toString());
                // 2.使用部门信息，执行查询部门SQL
                System.out.println("Department Message："+employee.getDeps());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(sqlSession!=null)
                sqlSession.close();
        }
    }
}
