package com.josen.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * @InterfaceName SetmealAndGroupMapper
 * @Description CheckGroup 和 Setmeal中间关系表Mapper
 * @Author Josen
 * @Create 2020/9/8 11:42
 */
public interface SetmealAndGroupMapper {
     void addRecord(@Param("setmealId") Integer sid,@Param("groupId") Integer gid);

     /**
      * 根据checkitemId 或 checkgroupId删除检查项
      * @param id
      * @param type false=根据checkgroupId true=根据setmealId
      */
     void deleteById(@Param("id") Integer id,@Param("type") boolean type);

}
