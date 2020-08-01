package com.josen.service;

import com.josen.beans.Location;
import com.josen.dao.LocationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName LocationService
 * @Description Details
 * @Author Josen
 * @Create 2020/8/1 19:28
 */
@Service
public class LocationService {
    @Autowired
    private LocationDao locationDao;
    public List<Location> getLocationList(){
        return locationDao.getLocationList();
    }
}
