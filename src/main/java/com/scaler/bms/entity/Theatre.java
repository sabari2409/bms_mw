package com.scaler.bms.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Theatre extends BaseModel {

    private String name;
    private String address;
    @OneToMany(mappedBy = "theatre", cascade = CascadeType.ALL)
    private List<Auditorium> auditoriums;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
//    @OneToMany
//    @JoinColumn(name = "theatre_id")
//    private List<Auditorium> auditoriumList;

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

    public List<Auditorium> getAuditoriums() {
        return auditoriums;
    }

    public void setAuditoriums(List<Auditorium> auditoriums) {
        this.auditoriums = auditoriums;
    }
}
