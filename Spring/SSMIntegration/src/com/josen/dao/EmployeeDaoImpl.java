package com.josen.dao;

import com.josen.beans.Employee;
import com.josen.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName EmployeeDaoImpl
 * @Description Details
 * @Author Josen
 * @Create 2020/7/31 22:32
 */
@Repository
public class EmployeeDaoImpl implements EmployeeDao{
    @Autowired
    private EmployeeMapper employeeMapper;
    @Override
    public List<Employee> getEmployeeList() {
        List<Employee> list = employeeMapper.selectEmployeeList();
        System.out.println("list"+list.size());
        return list;
    }
}
