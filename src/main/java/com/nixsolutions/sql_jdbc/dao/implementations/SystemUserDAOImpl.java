package com.nixsolutions.sql_jdbc.dao.implementations;

import com.nixsolutions.sql_jdbc.ConnectionManager;
import com.nixsolutions.sql_jdbc.dao.interfaces.DAOInterface;
import com.nixsolutions.sql_jdbc.entity.SystemUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SystemUserDAOImpl implements DAOInterface<SystemUser> {
    private static final Logger logger = LogManager
            .getLogger(SystemUserDAOImpl.class);

    @Override public void add(SystemUser systemUser) {
        try (Connection c = ConnectionManager.getInstance()
                .getConnection(); PreparedStatement preparedStatement = c
                .prepareStatement("INSERT INTO system_user "
                        + "( first_name, last_name, login_id, passport_number, phone_number, address, birthday, role_id) "
                        + "VALUES (?,?,?,?,?,?,?,?) RETURNING id;");) {
            preparedStatement.setString(1, systemUser.getFirstName());
            preparedStatement.setString(2, systemUser.getLastName());
            preparedStatement.setLong(3, systemUser.getLoginId());
            preparedStatement.setString(4, systemUser.getPassportNumber());
            preparedStatement.setString(5, systemUser.getPhoneNumber());
            preparedStatement.setString(6, systemUser.getAddress());
            preparedStatement
                    .setDate(7, convertUtilToSql(systemUser.getBirthday()));
            preparedStatement.setLong(8, systemUser.getRoleId());
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            Long id = resultSet.getLong("id");
            systemUser.setId(id);
        } catch (SQLException | IOException e) {
            logger.error(e);
        }
    }

    private static java.sql.Date convertUtilToSql(java.util.Date uDate) {
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }

    @Override public List<SystemUser> getAll() {
        List<SystemUser> list = new ArrayList<>();
        try (Connection c = ConnectionManager.getInstance()
                .getConnection(); PreparedStatement preparedStatement = c
                .prepareStatement("SELECT * FROM system_user;");) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                SystemUser systemUser = new SystemUser();
                systemUser.setId((resultSet.getLong("id")));
                systemUser.setFirstName(resultSet.getString("first_name"));
                systemUser.setLastName(resultSet.getString("last_name"));
                systemUser.setLoginId(resultSet.getLong("login_id"));
                systemUser.setPassportNumber(
                        resultSet.getString("passport_number"));
                systemUser.setPhoneNumber(resultSet.getString("phone_number"));
                systemUser.setAddress(resultSet.getString("address"));
                systemUser.setBirthday(resultSet.getDate("birthday"));
                systemUser.setRoleId(resultSet.getLong("role_id"));
                list.add(systemUser);
            }
        } catch (SQLException | IOException e) {
            logger.error(e);
        }
        return list;
    }

    @Override public SystemUser getById(int id) {
        SystemUser systemUser = new SystemUser();
        try (Connection c = ConnectionManager.getInstance()
                .getConnection(); PreparedStatement preparedStatement = c
                .prepareStatement("SELECT * FROM system_user WHERE id = ?;");) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            systemUser.setId((resultSet.getLong("id")));
            systemUser.setFirstName(resultSet.getString("first_name"));
            systemUser.setLastName(resultSet.getString("last_name"));
            systemUser.setLoginId(resultSet.getLong("login_id"));
            systemUser
                    .setPassportNumber(resultSet.getString("passport_number"));
            systemUser.setPhoneNumber(resultSet.getString("phone_number"));
            systemUser.setAddress(resultSet.getString("address"));
            systemUser.setBirthday(resultSet.getDate("birthday"));
            systemUser.setRoleId(resultSet.getLong("role_id"));
        } catch (SQLException | IOException e) {
            logger.error(e);
        }
        return systemUser;
    }

    @Override public void delete(int id) {
        try (Connection c = ConnectionManager.getInstance()
                .getConnection(); PreparedStatement preparedStatement = c
                .prepareStatement("DELETE FROM system_user WHERE id = ?;");) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException | IOException e) {
            logger.error(e);
        }
    }

    @Override public void update(SystemUser systemUser) {
        try (Connection c = ConnectionManager.getInstance()
                .getConnection(); PreparedStatement preparedStatement = c
                .prepareStatement("UPDATE system_user "
                        + "SET first_name = ?, last_name = ?,"
                        + "login_id = ?, passport_number = ?,"
                        + "phone_number = ?, address = ?,"
                        + "birthday = ?, role_id = ? WHERE id = ?;");) {
            preparedStatement.setString(1, systemUser.getFirstName());
            preparedStatement.setString(2, systemUser.getLastName());
            preparedStatement.setLong(3, systemUser.getLoginId());
            preparedStatement.setString(4, systemUser.getPassportNumber());
            preparedStatement.setString(5, systemUser.getPhoneNumber());
            preparedStatement.setString(6, systemUser.getAddress());
            preparedStatement
                    .setDate(7, convertUtilToSql(systemUser.getBirthday()));
            preparedStatement.setLong(8, systemUser.getRoleId());
            preparedStatement.setLong(9, systemUser.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException | IOException e) {
            logger.error(e);
        }
    }
}
