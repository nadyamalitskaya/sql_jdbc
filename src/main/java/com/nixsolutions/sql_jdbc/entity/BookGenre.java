package com.nixsolutions.sql_jdbc.entity;

import java.io.Serializable;

public class BookGenre implements Serializable {
    private Long id;
    private Long bookId;
    private Long genreId;

    public BookGenre() {
    }

    public BookGenre(Long bookId, Long genreId) {
        this.bookId = bookId;
        this.genreId = genreId;
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

    public Long getGenreId() {
        return genreId;
    }

    public void setGenreId(Long genreId) {
        this.genreId = genreId;
    }
}
