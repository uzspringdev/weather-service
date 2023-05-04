package com.pro.weatherservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RainDto {
    private Long rainId;
    @JsonProperty("1h")
    private Double oneHour;

    public Long getRainId() {
        return rainId;
    }

    public void setRainId(Long rainId) {
        this.rainId = rainId;
    }

    public Double getOneHour() {
        return oneHour;
    }

    public void setOneHour(Double oneHour) {
        this.oneHour = oneHour;
    }
}