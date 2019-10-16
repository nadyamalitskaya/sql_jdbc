package com.nixsolutions.sql_jdbc.dao.implementations;

import com.nixsolutions.sql_jdbc.ConnectionManager;
import com.nixsolutions.sql_jdbc.dao.interfaces.DAOInterface;
import com.nixsolutions.sql_jdbc.entity.UserOrder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserOrderDAOImpl implements DAOInterface<UserOrder> {
    private static final Logger logger = LogManager
            .getLogger(UserOrderDAOImpl.class);

    @Override public void add(UserOrder userOrder) {
        try (Connection c = ConnectionManager.getInstance()
                .getConnection(); PreparedStatement preparedStatement = c
                .prepareStatement("INSERT INTO user_order "
                        + "(user_id, item_id, address, order_date, return_date, order_status_id) "
                        + " VALUES (?,?,?,?,?,?) RETURNING id;")) {
            preparedStatement.setLong(1, userOrder.getUserId());
            preparedStatement.setLong(2, userOrder.getItemId());
            preparedStatement.setString(3, userOrder.getAddress());
            preparedStatement
                    .setDate(4, convertUtilToSql(userOrder.getOrderDate()));
            preparedStatement
                    .setDate(5, convertUtilToSql(userOrder.getReturnDate()));
            preparedStatement.setLong(6, userOrder.getOrderStatusId());
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            Long id = resultSet.getLong("id");
            userOrder.setId(id);
        } catch (SQLException | IOException e) {
            logger.error(e);
        }
    }

    private static java.sql.Date convertUtilToSql(java.util.Date uDate) {
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }

    @Override public List<UserOrder> getAll() {
        List<UserOrder> list = new ArrayList<>();
        try (Connection c = ConnectionManager.getInstance()
                .getConnection(); PreparedStatement preparedStatement = c
                .prepareStatement("SELECT * FROM user_order;");) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                UserOrder userOrder = new UserOrder();
                userOrder.setId((resultSet.getLong("id")));
                userOrder.setUserId((resultSet.getLong("user_id")));
                userOrder.setItemId((resultSet.getLong("item_id")));
                userOrder.setAddress(resultSet.getString("address"));
                userOrder.setOrderDate(resultSet.getDate("order_date"));
                userOrder.setReturnDate(resultSet.getDate("return_date"));
                userOrder.setOrderStatusId(
                        (resultSet.getLong("order_status_id")));
                list.add(userOrder);
            }
        } catch (SQLException | IOException e) {
            logger.error(e);
        }
        return list;
    }

    @Override public UserOrder getById(int id) {
        UserOrder userOrder = new UserOrder();
        try (Connection c = ConnectionManager.getInstance()
                .getConnection(); PreparedStatement preparedStatement = c
                .prepareStatement("SELECT * FROM user_order WHERE id = ?;");) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            userOrder.setId((resultSet.getLong("id")));
            userOrder.setUserId((resultSet.getLong("user_id")));
            userOrder.setItemId((resultSet.getLong("item_id")));
            userOrder.setAddress(resultSet.getString("address"));
            userOrder.setOrderDate(resultSet.getDate("order_date"));
            userOrder.setReturnDate(resultSet.getDate("return_date"));
            userOrder.setOrderStatusId((resultSet.getLong("order_status_id")));
        } catch (SQLException | IOException e) {
            logger.error(e);
        }
        return userOrder;
    }

    @Override public void delete(int id) {
        try (Connection c = ConnectionManager.getInstance()
                .getConnection(); PreparedStatement preparedStatement = c
                .prepareStatement("DELETE FROM user_order WHERE id = ?;");) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException | IOException e) {
            logger.error(e);
        }
    }

    @Override public void update(UserOrder userOrder) {
        try (Connection c = ConnectionManager.getInstance()
                .getConnection(); PreparedStatement preparedStatement = c
                .prepareStatement("UPDATE user_order"
                        + " SET user_id = ?, item_id = ?, address= ?, order_date = ?, return_date = ?,"
                        + "order_status_id = ? WHERE id = ?;");) {
            preparedStatement.setLong(1, userOrder.getUserId());
            preparedStatement.setLong(2, userOrder.getItemId());
            preparedStatement.setString(3, userOrder.getAddress());
            preparedStatement
                    .setDate(4, convertUtilToSql(userOrder.getOrderDate()));
            preparedStatement
                    .setDate(5, convertUtilToSql(userOrder.getReturnDate()));
            preparedStatement.setLong(6, userOrder.getOrderStatusId());
            preparedStatement.setLong(7, userOrder.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException | IOException e) {
            logger.error(e);
        }
    }
}
