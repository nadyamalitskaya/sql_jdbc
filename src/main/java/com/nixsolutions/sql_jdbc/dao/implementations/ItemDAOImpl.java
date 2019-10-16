package com.nixsolutions.sql_jdbc.dao.implementations;

import com.nixsolutions.sql_jdbc.ConnectionManager;
import com.nixsolutions.sql_jdbc.dao.interfaces.DAOInterface;
import com.nixsolutions.sql_jdbc.entity.Item;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements DAOInterface<Item> {
    private static final Logger logger = LogManager
            .getLogger(ItemDAOImpl.class);

    @Override public void add(Item item) {
        try (Connection c = ConnectionManager.getInstance()
                .getConnection(); PreparedStatement preparedStatement = c
                .prepareStatement("INSERT INTO item "
                        + "(publish_year, publish_office, book_id, item_status_id)"
                        + " VALUES (?,?,?,?) RETURNING id");) {
            preparedStatement.setInt(1, item.getPublishYear());
            preparedStatement.setString(2, item.getPublishOffice());
            preparedStatement.setLong(3, item.getBookId());
            preparedStatement.setLong(4, item.getItemStatusId());
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            Long id = resultSet.getLong("id");
            item.setId(id);
        } catch (SQLException | IOException e) {
            logger.error(e);
        }
    }

    @Override public List<Item> getAll() {
        List<Item> list = new ArrayList<>();
        try (Connection c = ConnectionManager.getInstance()
                .getConnection(); PreparedStatement preparedStatement = c
                .prepareStatement("SELECT * FROM item;");) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Item item = new Item();
                item.setId((resultSet.getLong("id")));
                item.setPublishYear(resultSet.getInt("publish_year"));
                item.setPublishOffice(resultSet.getString("publish_office"));
                item.setBookId((resultSet.getLong("book_id")));
                item.setItemStatusId((resultSet.getLong("item_status_id")));
                list.add(item);
            }
        } catch (SQLException | IOException e) {
            logger.error(e);
        }
        return list;
    }

    @Override public Item getById(int id) {
        Item item = new Item();
        try (Connection c = ConnectionManager.getInstance()
                .getConnection(); PreparedStatement preparedStatement = c
                .prepareStatement("SELECT * FROM item WHERE id = ?;");) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            item.setId((resultSet.getLong("id")));
            item.setPublishYear(resultSet.getInt("publish_year"));
            item.setPublishOffice(resultSet.getString("publish_office"));
            item.setBookId((resultSet.getLong("book_id")));
            item.setItemStatusId((resultSet.getLong("item_status_id")));
        } catch (SQLException | IOException e) {
            logger.error(e);
        }
        return item;
    }

    @Override public void delete(int id) {
        try (Connection c = ConnectionManager.getInstance()
                .getConnection(); PreparedStatement preparedStatement = c
                .prepareStatement("DELETE FROM item WHERE id = ?;");) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException | IOException e) {
            logger.error(e);
        }
    }

    @Override public void update(Item item) {
        try (Connection c = ConnectionManager.getInstance()
                .getConnection(); PreparedStatement preparedStatement = c
                .prepareStatement("UPDATE item"
                        + " SET publish_year = ?, publish_office = ?, book_id = ?,"
                        + "item_status_id = ? WHERE id = ?;");) {
            preparedStatement.setInt(1, item.getPublishYear());
            preparedStatement.setString(2, item.getPublishOffice());
            preparedStatement.setLong(3, item.getBookId());
            preparedStatement.setLong(4, item.getItemStatusId());
            preparedStatement.setLong(5, item.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException | IOException e) {
            logger.error(e);
        }
    }
}
