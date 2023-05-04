package com.pro.weatherservice.dto;

public class CloudsDto {
    private Long cloudsId;

    private Integer all;

    public Long getCloudsId() {
        return cloudsId;
    }

    public void setCloudsId(Long cloudsId) {
        this.cloudsId = cloudsId;
    }

    public Integer getAll() {
        return all;
    }

    public void setAll(Integer all) {
        this.all = all;
    }
}
