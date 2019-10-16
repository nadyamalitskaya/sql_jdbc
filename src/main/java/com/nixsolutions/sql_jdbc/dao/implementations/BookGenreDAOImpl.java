package com.nixsolutions.sql_jdbc.dao.implementations;

import com.nixsolutions.sql_jdbc.ConnectionManager;
import com.nixsolutions.sql_jdbc.dao.interfaces.DAOInterface;
import com.nixsolutions.sql_jdbc.entity.BookGenre;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookGenreDAOImpl implements DAOInterface<BookGenre> {
    private static final Logger logger = LogManager
            .getLogger(BookGenreDAOImpl.class);

    @Override public void add(BookGenre bookGenre) {
        try (Connection c = ConnectionManager.getInstance()
                .getConnection(); PreparedStatement preparedStatement = c
                .prepareStatement(
                        "INSERT INTO book_genre " + "(book_id, genre_id) "
                                + "VALUES  (?,?) RETURNING id;");) {
            preparedStatement.setLong(1, bookGenre.getBookId());
            preparedStatement.setLong(2, bookGenre.getGenreId());
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            Long id = resultSet.getLong("id");
            bookGenre.setId(id);
        } catch (SQLException | IOException e) {
            logger.error(e);
        }
    }

    @Override public List<BookGenre> getAll() {
        List<BookGenre> list = new ArrayList<>();
        try (Connection c = ConnectionManager.getInstance()
                .getConnection(); PreparedStatement preparedStatement = c
                .prepareStatement("SELECT * FROM book_genre;");) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                BookGenre bookGenre = new BookGenre();
                bookGenre.setId((resultSet.getLong("id")));
                bookGenre.setBookId((resultSet.getLong("book_id")));
                bookGenre.setGenreId((resultSet.getLong("genre_id")));
                list.add(bookGenre);
            }
        } catch (SQLException | IOException e) {
            logger.error(e);
        }
        return list;
    }

    @Override public BookGenre getById(int id) {
        BookGenre bookGenre = new BookGenre();
        try (Connection c = ConnectionManager.getInstance()
                .getConnection(); PreparedStatement preparedStatement = c
                .prepareStatement("SELECT * FROM book_genre WHERE id = ?;");) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            bookGenre.setId((resultSet.getLong("id")));
            bookGenre.setBookId((resultSet.getLong("book_id")));
            bookGenre.setGenreId((resultSet.getLong("genre_id")));
        } catch (SQLException | IOException e) {
            logger.error(e);
        }
        return bookGenre;
    }

    @Override public void delete(int id) {
        try (Connection c = ConnectionManager.getInstance()
                .getConnection(); PreparedStatement preparedStatement = c
                .prepareStatement("DELETE FROM book_genre WHERE id = ?;");) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException | IOException e) {
            logger.error(e);
        }
    }

    @Override public void update(BookGenre bookGenre) {
        try (Connection c = ConnectionManager.getInstance()
                .getConnection(); PreparedStatement preparedStatement = c
                .prepareStatement("UPDATE book_genre SET book_id = ?,"
                        + " genre_id = ? WHERE id = ?;");) {
            preparedStatement.setLong(1, bookGenre.getBookId());
            preparedStatement.setLong(2, bookGenre.getGenreId());
            preparedStatement.setLong(3, bookGenre.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException | IOException e) {
            logger.error(e);
        }
    }
}
