package com.pro.weatherservice.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "clouds")
public class Clouds {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "alls")
    private Integer all;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAll() {
        return all;
    }

    public void setAll(Integer all) {
        this.all = all;
    }
}
