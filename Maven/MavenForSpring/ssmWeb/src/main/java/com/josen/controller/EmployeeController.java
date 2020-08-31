package com.josen.controller;

import com.josen.pojo.Employee;
import com.josen.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName EmployeeController
 * @Description
 * @Author Josen
 * @Create 2020/8/31 17:24
 */
@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @ResponseBody
    @RequestMapping("/getlist")
    public List<Employee> handlerGetList(){
        List<Employee> list = employeeService.getEmployeeList();
        return list;
    }

    @ResponseBody
    @RequestMapping("/test_update")
    public Map handlerUpdateEmp(){
        Employee emp = new Employee();
        emp.setEmployeeId(100);
        emp.setFirstName("Curry");
        boolean b = employeeService.updateEmpById(emp);
        Map res = new HashMap();
        if(b){
            res.put("code",200);
            res.put("message","update success!");
        }else{
            res.put("code",500);
            res.put("message","update fail!");
        }
        return res;
    }
}
