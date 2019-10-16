package com.nixsolutions.sql_jdbc.dao.implementations;

import com.nixsolutions.sql_jdbc.ConnectionManager;
import com.nixsolutions.sql_jdbc.dao.interfaces.DAOInterface;
import com.nixsolutions.sql_jdbc.entity.Book;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookDAOImpl implements DAOInterface<Book> {
    private static final Logger logger = LogManager
            .getLogger(BookDAOImpl.class);

    @Override public void add(Book book) {
        try (Connection c = ConnectionManager.getInstance()
                .getConnection(); PreparedStatement preparedStatement = c
                .prepareStatement("INSERT INTO book "
                        + "(address_in_storage, name, language, volume) "
                        + "VALUES (?,?,?,?) RETURNING id;");) {
            preparedStatement.setString(1, book.getAddressInStorage());
            preparedStatement.setString(2, book.getName());
            preparedStatement.setString(3, book.getLanguage());
            preparedStatement.setInt(4, book.getVolume());
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            Long id = resultSet.getLong("id");
            book.setId(id);
        } catch (SQLException | IOException e) {
            logger.error(e);
        }
    }

    @Override public List<Book> getAll() {
        List<Book> list = new ArrayList<>();
        try (Connection c = ConnectionManager.getInstance()
                .getConnection(); PreparedStatement preparedStatement = c
                .prepareStatement("SELECT * FROM book;");) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Book book = new Book();
                book.setId((resultSet.getLong("id")));
                book.setAddressInStorage(
                        resultSet.getString("address_in_storage"));
                book.setName(resultSet.getString("name"));
                book.setLanguage(resultSet.getString("language"));
                book.setVolume(resultSet.getInt("volume"));
                list.add(book);
            }
        } catch (SQLException | IOException e) {
            logger.error(e);
        }
        return list;
    }

    @Override public Book getById(int id) {
        Book book = new Book();
        try (Connection c = ConnectionManager.getInstance()
                .getConnection(); PreparedStatement preparedStatement = c
                .prepareStatement("SELECT * FROM book WHERE id = ?;");) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            book.setId((resultSet.getLong("id")));
            book.setAddressInStorage(resultSet.getString("address_in_storage"));
            book.setName(resultSet.getString("name"));
            book.setLanguage(resultSet.getString("language"));
            book.setVolume(resultSet.getInt("volume"));
        } catch (SQLException | IOException e) {
            logger.error(e);
        }
        return book;
    }

    @Override public void delete(int id) {
        try (Connection c = ConnectionManager.getInstance()
                .getConnection(); PreparedStatement preparedStatement = c
                .prepareStatement("DELETE FROM book WHERE id = ?;");) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException | IOException e) {
            logger.error(e);
        }
    }

    @Override public void update(Book book) {
        try (Connection c = ConnectionManager.getInstance()
                .getConnection(); PreparedStatement preparedStatement = c
                .prepareStatement("UPDATE book SET address_in_storage = ?,"
                        + " name = ?, language = ?, volume = ? WHERE id = ?;");) {
            preparedStatement.setString(1, book.getAddressInStorage());
            preparedStatement.setString(2, book.getName());
            preparedStatement.setString(3, book.getLanguage());
            preparedStatement.setInt(4, book.getVolume());
            preparedStatement.setLong(5, book.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException | IOException e) {
            logger.error(e);
        }
    }
}

