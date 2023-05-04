package com.pro.weatherservice.dto;

public class CoordDto {
    private Long coordId;
    private Double lon;
    private Double lat;

    public Long getCoordId() {
        return coordId;
    }

    public void setCoordId(Long coordId) {
        this.coordId = coordId;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }
}
