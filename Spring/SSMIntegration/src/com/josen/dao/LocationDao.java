package com.josen.dao;

import com.josen.beans.Location;
import com.josen.mapper.LocationMapper;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName LocationDao
 * @Description Details
 * @Author Josen
 * @Create 2020/8/1 19:22
 */
@Repository
public class LocationDao {
    @Autowired
    private LocationMapper locationMapper;
    public List<Location> getLocationList(){
        List<Location> list = locationMapper.queryLocationList();
        System.out.println("Locations List = "+list);
        return list;
    }
}
