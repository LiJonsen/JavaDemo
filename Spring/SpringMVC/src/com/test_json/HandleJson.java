package com.test_json;

import com.beans.JsonFormat;
import curd_example.beans.Employee;
import curd_example.dao.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;

/**
 * @ClassName HandleJson
 * @Description Details
 * @Author Josen
 * @Create 2020/7/30 17:16
 */
@Controller
public class HandleJson {
    @Autowired
    private EmployeeDao employeeDao;

    /**
     * @ResponseBody - 该注解支持响应Json
     * 响应Get/Post请求，返回Json格式数据
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/testJson",method = RequestMethod.GET)
    public Collection<Employee> responseJson(){
        return employeeDao.getAll();
    }


    @ResponseBody
    @RequestMapping(value = "/testPostJson",method = RequestMethod.POST)
    public Collection<Employee> resPostJson(){
        return employeeDao.getAll();
    }

    /**
     * 接收请求的Json数据
     * @return
     */
    @RequestMapping(value = "/getClientJson",method=RequestMethod.POST)
    public String handlerClientJson(JsonFormat json){
        System.out.println("Data="+json);
        return "success";
    }

}
