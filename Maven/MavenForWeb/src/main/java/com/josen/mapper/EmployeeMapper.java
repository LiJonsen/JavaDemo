package com.josen.mapper;

import com.josen.beans.Employee;

import java.util.List;

/**
 * @ClassName EmployeeMapper
 * @Description 员工接口Mapper
 * @Author Josen
 * @Create 2020/8/3 12:39
 */
public interface EmployeeMapper {
    /**
     * 查询员工列表
     * @return
     */
    List<Employee> selectEmployeeList();
}
