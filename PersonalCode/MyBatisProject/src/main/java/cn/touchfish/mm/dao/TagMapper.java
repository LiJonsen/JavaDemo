package cn.touchfish.mm.dao;

import cn.touchfish.mm.pojo.Tag;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @InterfaceName TagMapper
 * @Description 标签Mapper
 * @Author Josen
 * @Create 2020/8/20 9:21
 */
public interface TagMapper {
    List<Tag> queryTagByCid(int id);

    /**
     * 插入多条标签记录
     * @return
     */
    boolean insertBatchQuestionTag(@Param("tags") List<Tag> list);
}
