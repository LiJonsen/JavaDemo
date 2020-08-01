package com.josen.service;

import com.josen.beans.Employee;
import com.josen.dao.EmployeeDao;
import com.josen.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName EmployeeServiceImpl
 * @Description Details
 * @Author Josen
 * @Create 2020/7/31 22:02
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeDao employeeDao;
    @Override
    public List<Employee> getEmployeeList() {
        return employeeDao.getEmployeeList();
    }
}
