package com.scaler.bms.services.impl;

import com.scaler.bms.entity.City;
import com.scaler.bms.repository.CityRepository;
import com.scaler.bms.services.interfaces.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;

    /**
     * findById
     * get the city details by id
     * @param cityId
     * @return
     */
    public City findById(Integer cityId) {
        City c = cityRepository.findById(cityId).orElse(null);
        return c;
    }

}
