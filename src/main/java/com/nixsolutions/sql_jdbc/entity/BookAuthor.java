package com.nixsolutions.sql_jdbc.entity;

import java.io.Serializable;

public class BookAuthor implements Serializable {
    private Long id;
    private Long bookId;
    private Long authorId;

    public BookAuthor() {
    }

    public BookAuthor(Long bookId, Long authorId) {
        this.bookId = bookId;
        this.authorId = authorId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }
}
