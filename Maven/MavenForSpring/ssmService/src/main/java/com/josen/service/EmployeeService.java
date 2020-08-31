package com.josen.service;

import com.josen.dao.EmployeeDao;
import com.josen.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName EmployeeService
 * @Description
 * @Author Josen
 * @Create 2020/8/31 17:15
 */
@Service
public class EmployeeService {
    @Autowired
    private EmployeeDao employeeDao;
    public List<Employee> getEmployeeList(){
        List<Employee> employees = employeeDao.queryList();
        return employees;
    }
    // 测试AOP事务配置
    public boolean updateEmpById(Employee employee){
        boolean flag;
        String name = employee.getFirstName();

        employee.setFirstName("TestAop");
        employeeDao.updateEmpById(employee);
        System.out.println("Error before...");

//         Throw Error
        int i = 1/0;
        System.out.println(i);


        employee.setFirstName(name);
        System.out.println("Error after...");
        flag = employeeDao.updateEmpById(employee);

        return flag;
    }
}
