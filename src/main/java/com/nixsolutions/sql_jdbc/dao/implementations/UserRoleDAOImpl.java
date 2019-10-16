package com.nixsolutions.sql_jdbc.dao.implementations;

import com.nixsolutions.sql_jdbc.ConnectionManager;
import com.nixsolutions.sql_jdbc.dao.interfaces.DAOInterface;
import com.nixsolutions.sql_jdbc.entity.UserRole;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRoleDAOImpl implements DAOInterface<UserRole> {
    private static final Logger logger = LogManager.getLogger(UserRoleDAOImpl.class);

    @Override
    public void add(UserRole userRole) {
        try (Connection c = ConnectionManager.getInstance().getConnection();
             PreparedStatement preparedStatement = c.prepareStatement("INSERT INTO user_role (role_name) " +
                     " VALUES (?) RETURNING id;");) {
            c.setAutoCommit(false);
            preparedStatement.setString(1, userRole.getRoleName());
            ResultSet resultSet;
            try {
                resultSet = preparedStatement.executeQuery();
                resultSet.next();
                Long id = resultSet.getLong("id");
                userRole.setId(id);
                c.commit();
            } catch (SQLException e) {
                c.rollback();
                logger.error(e);
            }
        } catch (SQLException | IOException e) {
            logger.error(e);
        }
    }

    @Override
    public List<UserRole> getAll() {
        List<UserRole> list = new ArrayList<>();
        try (Connection c = ConnectionManager.getInstance().getConnection();
             PreparedStatement preparedStatement = c.prepareStatement("SELECT * FROM user_role;");) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                UserRole userRole = new UserRole();
                userRole.setId((resultSet.getLong("id")));
                userRole.setRoleName(resultSet.getString("role_name"));
                list.add(userRole);
            }
        } catch (SQLException | IOException e) {
            logger.error(e);
        }
        return list;
    }

    @Override
    public UserRole getById(int id) {
        UserRole userRole = new UserRole();
        try (Connection c = ConnectionManager.getInstance().getConnection();
             PreparedStatement preparedStatement = c.prepareStatement("SELECT * FROM user_role WHERE id = ?;");) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            userRole.setId((resultSet.getLong("id")));
            userRole.setRoleName(resultSet.getString("role_name"));
        } catch (SQLException | IOException e) {
            logger.error(e);
        }
        return userRole;
    }

    @Override
    public void delete(int id) {
        try (Connection c = ConnectionManager.getInstance().getConnection();
             PreparedStatement preparedStatement = c.prepareStatement("DELETE FROM user_role WHERE id = ?;");) {
            c.setAutoCommit(false);
            preparedStatement.setLong(1, id);
            try {
                preparedStatement.executeUpdate();
                c.commit();
            } catch (SQLException e) {
                c.rollback();
                logger.error(e);
            }
        } catch (SQLException | IOException e) {
            logger.error(e);
        }
    }

    @Override
    public void update(UserRole userRole) {
        try (Connection c = ConnectionManager.getInstance().getConnection();
             PreparedStatement preparedStatement = c.prepareStatement("UPDATE user_role SET role_name = ? WHERE id = ?;");)
        {
            c.setAutoCommit(false);
            preparedStatement.setString(1, userRole.getRoleName());
            preparedStatement.setLong(2, userRole.getId());
            try {
                preparedStatement.executeUpdate();
                c.commit();
            } catch (SQLException e) {
                c.rollback();
                logger.error(e);
            }
        } catch (SQLException | IOException e) {
            logger.error(e);
        }
    }
}
