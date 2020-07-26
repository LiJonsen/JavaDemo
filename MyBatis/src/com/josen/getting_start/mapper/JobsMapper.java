package com.josen.getting_start.mapper;

import com.josen.getting_start.bean.Job;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @ClassName EmployeeMapper
 * @Description Details
 * @Author Josen
 * @Create 20:47 20:47
 */
public interface JobsMapper {
    // 根据id查询myemployees表一条记录
    Job queryOne(String jobId);
    // 查询所有记录
    List<Job> queryAll();
    // 新增一条记录
    Boolean insertOne(Job emp);
    // 更新一条记录
    Boolean updateOne(Job emp);
    // 删除一条记录
    Boolean deleteOne(String jobId);

    /**
     * 根据job_id&max_salary查询指定记录
     * 使用@Param注解指定参数名称，提供SQL映射文件#{name}获取
     * @param id jobId
     * @param salary maxSalary
     * @return
     */
    Job queryByJobIdAndMaxSalary(@Param("jobId") String id, @Param("maxSalary") Double salary);

    /**
     * 使用Map传递参数
     * @param map
     * @return
     */
    List<Job> queryJobForMap(Map<String,Object> map);
}
