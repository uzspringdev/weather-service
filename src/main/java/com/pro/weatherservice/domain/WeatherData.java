package com.pro.weatherservice.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "weather_data")
public class WeatherData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "weather_data_id")
    private Integer weatherDataId;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Coord coord;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "weather_data_weather",
    joinColumns = @JoinColumn(name = "weather_data_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "weather_id", referencedColumnName = "id"))
    private List<Weather> weather;

    @Column(name = "base")
    private String base;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "main_id", referencedColumnName = "id", unique = true)
    private Main main;

    @Column(name = "visibility")
    private Integer visibility;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "wind_id", referencedColumnName = "id", unique = true)
    private Wind wind;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "rain_id", referencedColumnName = "id", unique = true)
    private Rain rain;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "clouds_id", referencedColumnName = "id", unique = true)
    private Clouds clouds;

    @Column(name = "dt")
    private Long dt;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "sys_id", referencedColumnName = "id", unique = true)
    private Sys sys;

    @Column(name = "timezone")
    private Integer timezone;

    @Column(name = "name")
    private String name;

    @Column(name = "cod")
    private Integer cod;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getWeatherDataId() {
        return weatherDataId;
    }

    public void setWeatherDataId(Integer weatherDataId) {
        this.weatherDataId = weatherDataId;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Integer getVisibility() {
        return visibility;
    }

    public void setVisibility(Integer visibility) {
        this.visibility = visibility;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Rain getRain() {
        return rain;
    }

    public void setRain(Rain rain) {
        this.rain = rain;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public Long getDt() {
        return dt;
    }

    public void setDt(Long dt) {
        this.dt = dt;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public Integer getTimezone() {
        return timezone;
    }

    public void setTimezone(Integer timezone) {
        this.timezone = timezone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }
}
