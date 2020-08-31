package com.josen.dao;

import com.josen.pojo.Employee;

import java.util.List;

/**
 * @InterfaceName EmployeeDao
 * @Description
 * @Author Josen
 * @Create 2020/8/31 15:06
 */
public interface EmployeeDao {
    List<Employee> queryList();

    boolean updateEmpById(Employee employee);
}
