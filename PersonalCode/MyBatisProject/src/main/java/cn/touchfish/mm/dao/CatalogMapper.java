package cn.touchfish.mm.dao;

import cn.touchfish.mm.pojo.Catalog;
import java.util.List;

/**
 * @InterfaceName CatalogMapper
 * @Description 目录Mapper
 * @Author Josen
 * @Create 2020/8/20 9:21
 */
public interface CatalogMapper {
    List<Catalog> queryCatalogByCid(int id);

}
