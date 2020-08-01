package com.josen.handler;

import com.josen.beans.Employee;
import com.josen.beans.ResponseData;
import com.josen.service.EmployeeService;
import com.josen.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName EmployeeHandler
 * @Description Details
 * @Author Josen
 * @Create 2020/7/31 21:21
 */
@Controller
public class EmployeeHandler {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ResponseData res;
    @Autowired
    private LocationService locationService;
    /**
     * 处理员工列表请求
     * @return
     */
    @ResponseBody
    @RequestMapping("/employees")
    public ResponseData handlerEmployees(){
        res.setMessage("response finish!");

        // 整合data数据
        Map data = new HashMap<>();
        data.put("list",employeeService.getEmployeeList());
        data.put("current",1);
        data.put("count",10);
        data.put("total",100);
        res.setData(data);
        return res;
    }
    @ResponseBody
    @RequestMapping("/locations")
    public ResponseData handlerLocations(){
        res.setMessage("response finish!");
        // 整合data数据
        Map data = new HashMap<>();
        data.put("list",locationService.getLocationList());
        data.put("current",1);
        data.put("count",10);
        data.put("total",100);
        res.setData(data);
        return res;
    }

    @RequestMapping(value = "/getClientJson",method = RequestMethod.POST)
    public String handlerClientJson(@RequestBody Map obj){
        System.out.println("Print Client Json："+obj);
        return "success";
    }
}
