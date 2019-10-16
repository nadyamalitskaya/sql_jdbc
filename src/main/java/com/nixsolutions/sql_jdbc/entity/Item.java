package com.nixsolutions.sql_jdbc.entity;

import java.io.Serializable;

public class Item implements Serializable {
    private Long id;
    private int publishYear;
    private String publishOffice;
    private Long bookId;
    private Long itemStatusId;

    public Item() {
    }

    public Item(int publishYear, String publishOffice, Long bookId, Long itemStatusId) {
        this.publishYear = publishYear;
        this.publishOffice = publishOffice;
        this.bookId = bookId;
        this.itemStatusId = itemStatusId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    public String getPublishOffice() {
        return publishOffice;
    }

    public void setPublishOffice(String publishOffice) {
        this.publishOffice = publishOffice;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getItemStatusId() {
        return itemStatusId;
    }

    public void setItemStatusId(Long itemStatusId) {
        this.itemStatusId = itemStatusId;
    }
}
