package com.scaler.bms.dto;

import com.scaler.bms.entity.Auditorium;

import java.util.List;

public class TheatreResDTO {

    private Integer id;
    private String name;
    private String address;
    private List<Auditorium> auditoriums;

    public TheatreResDTO() {
    }

    public TheatreResDTO(Integer id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public List<Auditorium> getAuditoriums() {
        return auditoriums;
    }

    public void setAuditoriums(List<Auditorium> auditoriums) {
        this.auditoriums = auditoriums;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
