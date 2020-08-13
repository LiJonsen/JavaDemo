package com.josen.service;

import com.josen.beans.Employee;

import java.util.List;

/**
 * @InterfaceName EmployeeService
 * @Description Details
 * @Author Josen
 * @Create 2020/8/3 14:24
 */
public interface EmployeeService {
    // 处理员工列表业务逻辑
    List<Employee> dealWithEmployeeList();
}
