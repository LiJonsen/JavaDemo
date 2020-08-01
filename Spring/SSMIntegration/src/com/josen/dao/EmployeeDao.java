package com.josen.dao;

import com.josen.beans.Employee;

import java.util.List;

/**
 * @InterfaceName EmployeeDao
 * @Description Details
 * @Author Josen
 * @Create 2020/7/31 22:31
 */
public interface EmployeeDao {
    List<Employee> getEmployeeList();
}
