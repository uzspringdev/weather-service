package com.pro.weatherservice.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.pro.weatherservice.model.LangKey;
import com.pro.weatherservice.model.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_account_id", referencedColumnName = "id", unique = true)
    private UserAccount userAccount;

    @Column(name = "first_name")
    @Size(min = 2, max = 60)
    private String firstName;


    @Column(name = "last_name")
    @Size(min = 2, max = 60)
    private String lastName;

    @Column(name = "phone_number", unique = true, nullable = false)
    @Size(min = 7, max = 60)
    private String phoneNumber;

    @Column(name = "email", unique = true, nullable = false)
    @Size(min = 5, max = 60)
    private String email;

    @ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_cities",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "city_id", referencedColumnName = "id")})
    private Set<City> cities = new HashSet<>();


    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status = Status.ENABLE;

    @Column(name = "lang_key")
    @Enumerated(EnumType.STRING)
    private LangKey langKey = LangKey.UZ;

    @Column(name = "created_at", updatable = false)
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<City> getCities() {
        return cities;
    }

    public void setCities(Set<City> cities) {
        this.cities = cities;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LangKey getLangKey() {
        return langKey;
    }

    public void setLangKey(LangKey langKey) {
        this.langKey = langKey;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}

