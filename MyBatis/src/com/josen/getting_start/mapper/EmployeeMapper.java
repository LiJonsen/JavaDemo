package com.josen.getting_start.mapper;

import com.josen.getting_start.bean.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapper {
    /**
     * 分步查询
     * ①-查询>=salary的员工信息
     * ②-根据返回的department_id查询部门信息
     * @param salary
     * @return
     */
    List<Employee> queryEmployeeBySalaryForStep(Double salary);

    /**
     * 动态SQL查询-01
     * @param condition 对应的属性值为where查询条件
     * @return
     */
    Employee queryEmployeeForDynamicSql(Employee condition);

    /**
     * 查询ids数组中的id对应的员工信息
     * @param ids
     * @return
     */
    List<Employee> queryEmployeesByForeach(@Param("e_ids") int[] ids);
}
