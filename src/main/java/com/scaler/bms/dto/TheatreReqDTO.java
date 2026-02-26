package com.scaler.bms.dto;

import com.scaler.bms.constants.CityType;


public class TheatreReqDTO {

    private Integer cityId;
    private String name;
    private CityType cityType;
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public TheatreReqDTO() {
    }

    public TheatreReqDTO(Integer cId, String name, CityType cType) {
        this.cityId = cId;
        this.name = name;
        this.cityType = cType;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CityType getCityType() {
        return cityType;
    }

    public void setCityType(CityType cityType) {
        this.cityType = cityType;
    }
}
