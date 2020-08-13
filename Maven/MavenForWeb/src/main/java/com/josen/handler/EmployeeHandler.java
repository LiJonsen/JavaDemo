package com.josen.handler;

import com.josen.beans.Employee;
import com.josen.mapper.EmployeeMapper;
import com.josen.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.*;

/**
 * @ClassName EmployeeHandler
 * @Description 员工处理器
 * @Author Josen
 * @Create 2020/8/3 10:53
 */
@Controller
public class EmployeeHandler {
    @Autowired
    private EmployeeService employeeService;



    @ResponseBody
    @RequestMapping(value = "getEmployeeList",method = RequestMethod.POST)
    public List<Employee> handlerEmployeeList(){

        return employeeService.dealWithEmployeeList();
    }
}
