package com.nixsolutions.sql_jdbc.dao.implementations;

import com.nixsolutions.sql_jdbc.ConnectionManager;
import com.nixsolutions.sql_jdbc.dao.interfaces.DAOInterface;
import com.nixsolutions.sql_jdbc.entity.Author;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorDAOImpl implements DAOInterface<Author> {
    private static final Logger logger = LogManager
            .getLogger(AuthorDAOImpl.class);

    @Override public void add(Author author) {

        try (Connection c = ConnectionManager.getInstance()
                .getConnection(); PreparedStatement preparedStatement = c
                .prepareStatement("INSERT INTO author "
                        + "(first_name, middle_name, last_name, sex, birthday) "
                        + "VALUES  (?,?,?,?,?) RETURNING id;");) {
            preparedStatement.setString(1, author.getFirstName());
            preparedStatement.setString(2, author.getMiddleName());
            preparedStatement.setString(3, author.getLastName());
            preparedStatement.setString(4, author.getSex());
            preparedStatement
                    .setDate(5, convertUtilToSql(author.getBirthday()));
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            Long id = resultSet.getLong("id");
            author.setId(id);
        } catch (SQLException | IOException e) {
            logger.error(e);
        }
    }

    private static java.sql.Date convertUtilToSql(java.util.Date uDate) {
        if (uDate == null)
            return null;
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }

    @Override public List<Author> getAll() {
        List<Author> list = new ArrayList<>();
        try (Connection c = ConnectionManager.getInstance()
                .getConnection(); PreparedStatement preparedStatement = c
                .prepareStatement("SELECT * FROM author;");) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Author author = new Author();
                author.setId((resultSet.getLong("id")));
                author.setFirstName(resultSet.getString("first_name"));
                author.setMiddleName(resultSet.getString("middle_name"));
                author.setLastName(resultSet.getString("last_name"));
                author.setSex(resultSet.getString("sex"));
                author.setBirthday(resultSet.getDate("birthday"));
                list.add(author);
            }
        } catch (SQLException | IOException e) {
            logger.error(e);
        }
        return list;
    }

    @Override public Author getById(int id) {
        Author author = new Author();
        try (Connection c = ConnectionManager.getInstance()
                .getConnection(); PreparedStatement preparedStatement = c
                .prepareStatement("SELECT * FROM author WHERE id = ?;");) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            author.setId((resultSet.getLong("id")));
            author.setFirstName(resultSet.getString("first_name"));
            author.setMiddleName(resultSet.getString("middle_name"));
            author.setLastName(resultSet.getString("last_name"));
            author.setSex(resultSet.getString("sex"));
            author.setBirthday(resultSet.getDate("birthday"));
            resultSet.close();
        } catch (SQLException | IOException e) {
            logger.error(e);
        }
        return author;
    }

    @Override public void delete(int id) {
        try (Connection c = ConnectionManager.getInstance()
                .getConnection(); PreparedStatement preparedStatement = c
                .prepareStatement("DELETE FROM author WHERE id = ?;");) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException | IOException e) {
            logger.error(e);
        }
    }

    @Override public void update(Author author) {
        try (Connection c = ConnectionManager.getInstance()
                .getConnection(); PreparedStatement preparedStatement = c
                .prepareStatement("UPDATE author SET first_name = ?,"
                        + "middle_name = ?, last_name = ?, sex = ?, birthday = ? WHERE id = ?;");) {
            preparedStatement.setString(1, author.getFirstName());
            preparedStatement.setString(2, author.getMiddleName());
            preparedStatement.setString(3, author.getLastName());
            preparedStatement.setString(4, author.getSex());
            preparedStatement
                    .setDate(5, convertUtilToSql(author.getBirthday()));
            preparedStatement.setLong(6, author.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException | IOException e) {
            logger.error(e);
        }
    }
}

