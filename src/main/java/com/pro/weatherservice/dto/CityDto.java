package com.pro.weatherservice.dto;

import com.pro.weatherservice.model.Coord;
import jakarta.persistence.Embedded;
import jakarta.persistence.Id;

public class CityDto {
    private Long id;
    private String name;
    private String state;
    private String country;
    private CoordDto coord;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public CoordDto getCoord() {
        return coord;
    }

    public void setCoord(CoordDto coord) {
        this.coord = coord;
    }
}
