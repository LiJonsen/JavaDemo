package com.josen.service;

import com.josen.beans.Employee;

import java.util.List;

/**
 * @InterfaceName EmployeeService
 * @Description 员工服务层
 * @Author Josen
 * @Create 2020/7/31 21:23
 */
public interface EmployeeService {
    List<Employee> getEmployeeList();
}
