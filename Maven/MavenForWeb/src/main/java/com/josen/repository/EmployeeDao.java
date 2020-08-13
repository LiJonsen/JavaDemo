package com.josen.repository;

import com.josen.beans.Employee;

import java.util.List;

/**
 * @InterfaceName EmployeeDao
 * @Description Details
 * @Author Josen
 * @Create 2020/8/3 12:44
 */
public interface EmployeeDao {
    List<Employee> queryEmployeeList();
}
