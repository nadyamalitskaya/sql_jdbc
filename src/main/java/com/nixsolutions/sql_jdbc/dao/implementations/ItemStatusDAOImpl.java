package com.nixsolutions.sql_jdbc.dao.implementations;

import com.nixsolutions.sql_jdbc.ConnectionManager;
import com.nixsolutions.sql_jdbc.dao.interfaces.DAOInterface;
import com.nixsolutions.sql_jdbc.entity.ItemStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemStatusDAOImpl implements DAOInterface<ItemStatus> {
    private static final Logger logger = LogManager
            .getLogger(ItemStatusDAOImpl.class);

    @Override public void add(ItemStatus itemStatus) {
        try (Connection c = ConnectionManager.getInstance()
                .getConnection(); PreparedStatement preparedStatement = c
                .prepareStatement("INSERT INTO item_status (name) "
                        + " VALUES (?) RETURNING id");) {
            preparedStatement.setString(1, itemStatus.getName());
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            Long id = resultSet.getLong("id");
            itemStatus.setId(id);
        } catch (SQLException | IOException e) {
            logger.error(e);
        }
    }

    @Override public List<ItemStatus> getAll() {
        List<ItemStatus> list = new ArrayList<>();
        try (Connection c = ConnectionManager.getInstance()
                .getConnection(); PreparedStatement preparedStatement = c
                .prepareStatement("SELECT * FROM item_status;");) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ItemStatus itemStatus = new ItemStatus();
                itemStatus.setId((resultSet.getLong("id")));
                itemStatus.setName(resultSet.getString("name"));
                list.add(itemStatus);
            }
        } catch (SQLException | IOException e) {
            logger.error(e);
        }
        return list;
    }

    @Override public ItemStatus getById(int id) {
        ItemStatus itemStatus = new ItemStatus();
        try (Connection c = ConnectionManager.getInstance()
                .getConnection(); PreparedStatement preparedStatement = c
                .prepareStatement("SELECT * FROM item_status WHERE id = ?;");) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            itemStatus.setId((resultSet.getLong("id")));
            itemStatus.setName(resultSet.getString("name"));
        } catch (SQLException | IOException e) {
            logger.error(e);
        }
        return itemStatus;
    }

    @Override public void delete(int id) {
        try (Connection c = ConnectionManager.getInstance()
                .getConnection(); PreparedStatement preparedStatement = c
                .prepareStatement("DELETE FROM item_status WHERE id = ?;");) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException | IOException e) {
            logger.error(e);
        }
    }

    @Override public void update(ItemStatus itemStatus) {
        try (Connection c = ConnectionManager.getInstance()
                .getConnection(); PreparedStatement preparedStatement = c
                .prepareStatement("UPDATE item_status"
                        + " SET name = ? WHERE id = ?;");) {
            preparedStatement.setString(1, itemStatus.getName());
            preparedStatement.setLong(2, itemStatus.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException | IOException e) {
            logger.error(e);
        }
    }
}
