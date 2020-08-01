package com.josen.mapper;

import com.josen.beans.Employee;

import java.util.List;

/**
 * @InterfaceName EmployeeMapper
 * @Description Details
 * @Author Josen
 * @Create 2020/7/31 21:16
 */
public interface EmployeeMapper {
    /**
     * 查询员工列表
     * @return
     */
    List<Employee> selectEmployeeList();
}
