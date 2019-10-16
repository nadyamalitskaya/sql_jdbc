package com.nixsolutions.sql_jdbc.entity;

import java.io.Serializable;
import java.util.Date;

public class UserOrder implements Serializable {
    private Long id;
    private Long userId;
    private Long itemId;
    private String address;
    private Date orderDate;
    private Date returnDate;
    private Long orderStatusId;

    public UserOrder() {
    }

    public UserOrder(Long userId, Long itemId, String address, Date orderDate, Date returnDate, Long orderStatusId) {
        this.userId = userId;
        this.itemId = itemId;
        this.address = address;
        this.orderDate = orderDate;
        this.returnDate = returnDate;
        this.orderStatusId = orderStatusId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Long getOrderStatusId() {
        return orderStatusId;
    }

    public void setOrderStatusId(Long orderStatusId) {
        this.orderStatusId = orderStatusId;
    }
}
