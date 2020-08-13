package com.josen.repository;

import com.josen.beans.Employee;
import com.josen.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @InterfaceName EmployeeDao
 * @Description Details
 * @Author Josen
 * @Create 2020/8/3 12:44
 */
@Repository
public class EmployeeDaoImpl implements EmployeeDao{
    @Autowired
    private EmployeeMapper employeeMapper;
    @Override
    public List<Employee> queryEmployeeList() {
        return employeeMapper.selectEmployeeList();
    }
}
