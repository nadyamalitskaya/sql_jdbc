package com.nixsolutions.sql_jdbc.dao.implementations;

import com.nixsolutions.sql_jdbc.ConnectionManager;
import com.nixsolutions.sql_jdbc.dao.interfaces.DAOInterface;
import com.nixsolutions.sql_jdbc.entity.BookAuthor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookAuthorDAOImpl implements DAOInterface<BookAuthor> {
    private static final Logger logger = LogManager
            .getLogger(BookAuthorDAOImpl.class);

    @Override public void add(BookAuthor bookAuthor) {
        try (Connection c = ConnectionManager.getInstance()
                .getConnection(); PreparedStatement preparedStatement = c
                .prepareStatement("INSERT INTO book_author (book_id, author_id)"
                        + " VALUES (?,?) RETURNING id");) {
            preparedStatement.setLong(1, bookAuthor.getBookId());
            preparedStatement.setLong(2, bookAuthor.getAuthorId());
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            Long id = resultSet.getLong("id");
            bookAuthor.setId(id);
        } catch (SQLException | IOException e) {
            logger.error(e);
        }
    }

    @Override public List<BookAuthor> getAll() {
        List<BookAuthor> list = new ArrayList<>();
        try (Connection c = ConnectionManager.getInstance()
                .getConnection(); PreparedStatement preparedStatement = c
                .prepareStatement("SELECT * FROM book_author;");) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                BookAuthor bookAuthor = new BookAuthor();
                bookAuthor.setId((resultSet.getLong("id")));
                bookAuthor.setBookId((resultSet.getLong("book_id")));
                bookAuthor.setAuthorId((resultSet.getLong("author_id")));
                list.add(bookAuthor);
            }
        } catch (SQLException | IOException e) {
            logger.error(e);
        }
        return list;
    }

    @Override public BookAuthor getById(int id) {
        BookAuthor bookAuthor = new BookAuthor();
        try (Connection c = ConnectionManager.getInstance()
                .getConnection(); PreparedStatement preparedStatement = c
                .prepareStatement("SELECT * FROM book_author WHERE id = ?;");) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            bookAuthor.setId((resultSet.getLong("id")));
            bookAuthor.setBookId((resultSet.getLong("book_id")));
            bookAuthor.setAuthorId((resultSet.getLong("author_id")));
        } catch (SQLException | IOException e) {
            logger.error(e);
        }
        return bookAuthor;
    }

    @Override public void delete(int id) {
        try (Connection c = ConnectionManager.getInstance()
                .getConnection(); PreparedStatement preparedStatement = c
                .prepareStatement("DELETE FROM book_author WHERE id = ?;");) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException | IOException e) {
            logger.error(e);
        }
    }

    @Override public void update(BookAuthor bookAuthor) {
        try (Connection c = ConnectionManager.getInstance()
                .getConnection(); PreparedStatement preparedStatement = c
                .prepareStatement(
                        "UPDATE book_author SET book_id = ?, " + "author_id = ?"
                                + " WHERE id = ?;");) {
            preparedStatement.setLong(1, bookAuthor.getBookId());
            preparedStatement.setLong(2, bookAuthor.getAuthorId());
            preparedStatement.setLong(3, bookAuthor.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException | IOException e) {
            logger.error(e);
        }
    }
}
