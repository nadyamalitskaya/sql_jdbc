package com.nixsolutions.sql_jdbc.dao.implementations;

import com.nixsolutions.sql_jdbc.ConnectionManager;
import com.nixsolutions.sql_jdbc.dao.interfaces.DAOInterface;
import com.nixsolutions.sql_jdbc.entity.UserLogin;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserLoginDAOImpl implements DAOInterface<UserLogin> {
    private static final Logger logger = LogManager
            .getLogger(UserLoginDAOImpl.class);

    @Override public void add(UserLogin userLogin) {
        try (Connection c = ConnectionManager.getInstance()
                .getConnection(); PreparedStatement preparedStatement = c
                .prepareStatement(
                        "INSERT INTO user_login (login_name, password, email) "
                                + " VALUES (?,?,?) RETURNING id;");) {
            preparedStatement.setString(1, userLogin.getLoginName());
            preparedStatement.setString(2, userLogin.getPassword());
            preparedStatement.setString(3, userLogin.getEmail());
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            Long id = resultSet.getLong("id");
            userLogin.setId(id);
        } catch (SQLException | IOException e) {
            logger.error(e);
        }
    }

    @Override public List<UserLogin> getAll() {
        List<UserLogin> list = new ArrayList<>();
        try (Connection c = ConnectionManager.getInstance()
                .getConnection(); PreparedStatement preparedStatement = c
                .prepareStatement("SELECT * FROM user_login;");) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                UserLogin userLogin = new UserLogin();
                userLogin.setId((resultSet.getLong("id")));
                userLogin.setLoginName(resultSet.getString("login_name"));
                userLogin.setPassword(resultSet.getString("password"));
                userLogin.setEmail(resultSet.getString("email"));
                list.add(userLogin);
            }
        } catch (SQLException | IOException e) {
            logger.error(e);
        }
        return list;
    }

    @Override public UserLogin getById(int id) {
        UserLogin userLogin = new UserLogin();
        try (Connection c = ConnectionManager.getInstance()
                .getConnection(); PreparedStatement preparedStatement = c
                .prepareStatement("SELECT * FROM user_login WHERE id = ?;");) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            userLogin.setId((resultSet.getLong("id")));
            userLogin.setLoginName(resultSet.getString("login_name"));
            userLogin.setPassword(resultSet.getString("password"));
            userLogin.setEmail(resultSet.getString("email"));
        } catch (SQLException | IOException e) {
            logger.error(e);
        }
        return userLogin;
    }

    @Override public void delete(int id) {
        try (Connection c = ConnectionManager.getInstance()
                .getConnection(); PreparedStatement preparedStatement = c
                .prepareStatement("DELETE FROM user_login WHERE id = ?;");) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException | IOException e) {
            logger.error(e);
        }
    }

    @Override public void update(UserLogin userLogin) {
        try (Connection c = ConnectionManager.getInstance()
                .getConnection(); PreparedStatement preparedStatement = c
                .prepareStatement("UPDATE user_login SET login_name = ?,"
                        + "password = ?, email = ? WHERE id = ?;");) {
            preparedStatement.setString(1, userLogin.getLoginName());
            preparedStatement.setString(2, userLogin.getPassword());
            preparedStatement.setString(3, userLogin.getEmail());
            preparedStatement.setLong(4, userLogin.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException | IOException e) {
            logger.error(e);
        }
    }
}
