package cn.touchfish.mm.dao;

import cn.touchfish.mm.pojo.Dict;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @InterfaceName DictMapper
 * @Description 城市列表Mapper
 * @Author Josen
 * @Create 2020/8/23 12:09
 */
public interface DictMapper {
    /**
     * 根据fs类型获取城市列表
     * fs=0：不首页显示 - 所有城市
     * fs=1：首页显示-热门城市
     * @param fs
     * @return
     */
    List<Dict> getDictListByFs(@Param("dataTag") int fs);

    /**
     * 根据城市名查询
     * @param name
     * @return
     */
    Dict getDictByCityName(@Param("dataValue") String name);
}
