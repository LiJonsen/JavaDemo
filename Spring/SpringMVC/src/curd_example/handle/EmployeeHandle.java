package curd_example.handle;

import curd_example.beans.Employee;
import curd_example.dao.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;

/**
 * @ClassName EmployeeHandle
 * @Description Details
 * @Author Josen
 * @Create 2020/7/30 12:04
 */
@Controller
public class EmployeeHandle {
    @Autowired
    private EmployeeDao employeeDao;
    @RequestMapping(value = "/employeeList",method = RequestMethod.GET)
    public ModelAndView getEmpList(){
        Collection<Employee> list = employeeDao.getAll();
        // viewName=视图解析器要转发的路径名
        ModelAndView mv = new ModelAndView("employee");
        // 添加model数据，实际会存放到request域中
        mv.addObject("emps",list);
        return mv;
    }
}
