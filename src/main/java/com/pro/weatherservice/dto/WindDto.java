package com.pro.weatherservice.dto;

public class WindDto {
    private Long windId;
    private Double speed;
    private Integer deg;
    private Double gust;

    public Long getWindId() {
        return windId;
    }

    public void setWindId(Long windId) {
        this.windId = windId;
    }



    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Integer getDeg() {
        return deg;
    }

    public void setDeg(Integer deg) {
        this.deg = deg;
    }

    public Double getGust() {
        return gust;
    }

    public void setGust(Double gust) {
        this.gust = gust;
    }
}