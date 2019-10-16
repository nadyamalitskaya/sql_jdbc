package com.nixsolutions.sql_jdbc.dao.implementations;

import com.nixsolutions.sql_jdbc.ConnectionManager;
import com.nixsolutions.sql_jdbc.dao.interfaces.DAOInterface;
import com.nixsolutions.sql_jdbc.entity.OrderStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderStatusDAOImpl implements DAOInterface<OrderStatus> {
    private static final Logger logger = LogManager
            .getLogger(OrderStatusDAOImpl.class);

    @Override public void add(OrderStatus orderStatus) {
        try (Connection c = ConnectionManager.getInstance()
                .getConnection(); PreparedStatement preparedStatement = c
                .prepareStatement("INSERT INTO order_status (name)"
                        + " VALUES (?) RETURNING id;");) {
            preparedStatement.setString(1, orderStatus.getName());
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            Long id = resultSet.getLong("id");
            orderStatus.setId(id);
        } catch (SQLException | IOException e) {
            logger.error(e);
        }
    }

    @Override public List<OrderStatus> getAll() {
        List<OrderStatus> list = new ArrayList<>();
        try (Connection c = ConnectionManager.getInstance()
                .getConnection(); PreparedStatement preparedStatement = c
                .prepareStatement("SELECT * FROM order_status;");) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                OrderStatus orderStatus = new OrderStatus();
                orderStatus.setId((resultSet.getLong("id")));
                orderStatus.setName(resultSet.getString("name"));
                list.add(orderStatus);
            }
        } catch (SQLException | IOException e) {
            logger.error(e);
        }
        return list;
    }

    @Override public OrderStatus getById(int id) {
        OrderStatus orderStatus = new OrderStatus();
        try (Connection c = ConnectionManager.getInstance()
                .getConnection(); PreparedStatement preparedStatement = c
                .prepareStatement(
                        "SELECT * FROM order_status WHERE id = ?;");) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            orderStatus.setId((resultSet.getLong("id")));
            orderStatus.setName(resultSet.getString("name"));
        } catch (SQLException | IOException e) {
            logger.error(e);
        }
        return orderStatus;
    }

    @Override public void delete(int id) {
        try (Connection c = ConnectionManager.getInstance()
                .getConnection(); PreparedStatement preparedStatement = c
                .prepareStatement("DELETE FROM order_status WHERE id = ?;");) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException | IOException e) {
            logger.error(e);
        }
    }

    @Override public void update(OrderStatus orderStatus) {
        try (Connection c = ConnectionManager.getInstance()
                .getConnection(); PreparedStatement preparedStatement = c
                .prepareStatement("UPDATE order_status"
                        + " SET name = ? WHERE id = ?;");) {
            preparedStatement.setString(1, orderStatus.getName());
            preparedStatement.setLong(2, orderStatus.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException | IOException e) {
            logger.error(e);
        }
    }
}
