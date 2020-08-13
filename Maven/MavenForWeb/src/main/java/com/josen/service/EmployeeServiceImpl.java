package com.josen.service;

import com.josen.beans.Employee;
import com.josen.repository.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName EmployeeServiceImpl
 * @Description Details
 * @Author Josen
 * @Create 2020/8/3 14:24
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeDao employeeDao;
    @Override
    public List<Employee> dealWithEmployeeList() {
        return employeeDao.queryEmployeeList();
    }
}
