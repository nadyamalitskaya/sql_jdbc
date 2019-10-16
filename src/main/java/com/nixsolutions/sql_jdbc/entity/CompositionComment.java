package com.nixsolutions.sql_jdbc.entity;

import java.io.Serializable;
import java.util.Date;

public class CompositionComment implements Serializable {
    private Long id;
    private Long userId;
    private Long bookId;
    private String comment;
    private Date commentDate;

    public CompositionComment() {
    }

    public CompositionComment(Long userId, Long bookId, String comment, Date commentDate) {
        this.userId = userId;
        this.bookId = bookId;
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

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
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
