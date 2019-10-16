package com.nixsolutions.sql_jdbc.entity;

import java.io.Serializable;

public class OrderStatus implements Serializable {
    private Long id;
    private String name;

    public OrderStatus() {
    }

    public OrderStatus(String name) {
        this.name = name;
    }

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
}
