package com.nixsolutions.sql_jdbc.entity;

import java.io.Serializable;
import java.util.Date;

public class StatusComment implements Serializable {
    private Long id;
    private Long userId;
    private Long itemId;
    private String comment;
    private Date commentDate;

    public StatusComment() {
    }

    public StatusComment(Long userId, Long itemId, String comment, Date commentDate) {
        this.userId = userId;
        this.itemId = itemId;
        this.comment = comment;
        this.commentDate = commentDate;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }
}
