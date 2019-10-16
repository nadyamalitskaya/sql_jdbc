package com.nixsolutions.sql_jdbc.entity;

import java.io.Serializable;

public class Book implements Serializable {
    private Long id;
    private String addressInStorage;
    private String name;
    private String language;
    private int volume;

    public Book() {
    }

    public Book(String addressInStorage, String name, String language, int volume) {
        this.addressInStorage = addressInStorage;
        this.name = name;
        this.language = language;
        this.volume = volume;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddressInStorage() {
        return addressInStorage;
    }

    public void setAddressInStorage(String addressInStorage) {
        this.addressInStorage = addressInStorage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }
}
