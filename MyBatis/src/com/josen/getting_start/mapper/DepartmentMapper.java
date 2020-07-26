package com.josen.getting_start.mapper;

import com.josen.getting_start.bean.Department;
import org.apache.ibatis.annotations.Param;

public interface DepartmentMapper {
    // 根据department_id查询指定部门+地址信息
    Department queryDepartmentAndLocation(@Param("departmentId") Integer id);
}
