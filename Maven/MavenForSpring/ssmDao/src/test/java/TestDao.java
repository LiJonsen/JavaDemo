import com.josen.dao.EmployeeDao;
import com.josen.pojo.Employee;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @ClassName TestDao
 * @Description Details
 * @Author Josen
 * @Create 2020/8/31 15:51
 */

public class TestDao {

    @Test
    public void testing(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-dao.xml");
        EmployeeDao employeeDao = context.getBean(EmployeeDao.class);
        List<Employee> employees = employeeDao.queryList();
        for (Employee employee : employees) {
            System.out.println(employee);
        }

    }

}
