package com.nixsolutions.sql_jdbc.dao.implementations;

import com.nixsolutions.sql_jdbc.ConnectionManager;
import com.nixsolutions.sql_jdbc.dao.interfaces.DAOInterface;
import com.nixsolutions.sql_jdbc.entity.StatusComment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StatusCommentDAOImpl implements DAOInterface<StatusComment> {
    private static final Logger logger = LogManager
            .getLogger(StatusComment.class);

    @Override public void add(StatusComment statusComment) {
        try (Connection c = ConnectionManager.getInstance()
                .getConnection(); PreparedStatement preparedStatement = c
                .prepareStatement("INSERT INTO status_comment"
                        + " (user_id, item_id, comment, comment_date) "
                        + " VALUES (?,?,?,?) RETURNING id;");) {
            preparedStatement.setLong(1, statusComment.getUserId());
            preparedStatement.setLong(2, statusComment.getItemId());
            preparedStatement.setString(3, statusComment.getComment());
            preparedStatement.setDate(4,
                    convertUtilToSql(statusComment.getCommentDate()));
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            Long id = resultSet.getLong("id");
            statusComment.setId(id);
        } catch (SQLException | IOException e) {
            logger.error(e);
        }
    }

    private static java.sql.Date convertUtilToSql(java.util.Date uDate) {
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }

    @Override public List<StatusComment> getAll() {
        List<StatusComment> list = new ArrayList<>();
        try (Connection c = ConnectionManager.getInstance()
                .getConnection(); PreparedStatement preparedStatement = c
                .prepareStatement("SELECT * FROM status_comment;");) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                StatusComment compositionComment = new StatusComment();
                compositionComment.setId((resultSet.getLong("id")));
                compositionComment.setUserId((resultSet.getLong("user_id")));
                compositionComment.setItemId((resultSet.getLong("item_id")));
                compositionComment.setComment(resultSet.getString("comment"));
                compositionComment
                        .setCommentDate(resultSet.getDate("comment_date"));
                list.add(compositionComment);
            }
        } catch (SQLException | IOException e) {
            logger.error(e);
        }
        return list;
    }

    @Override public StatusComment getById(int id) {
        StatusComment compositionComment = new StatusComment();
        try (Connection c = ConnectionManager.getInstance()
                .getConnection(); PreparedStatement preparedStatement = c
                .prepareStatement(
                        "SELECT * FROM status_comment WHERE id = ?;");) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            compositionComment.setId((resultSet.getLong("id")));
            compositionComment.setUserId((resultSet.getLong("user_id")));
            compositionComment.setItemId((resultSet.getLong("item_id")));
            compositionComment.setComment(resultSet.getString("comment"));
            compositionComment
                    .setCommentDate(resultSet.getDate("comment_date"));
        } catch (SQLException | IOException e) {
            logger.error(e);
        }
        return compositionComment;
    }

    @Override public void delete(int id) {
        try (Connection c = ConnectionManager.getInstance()
                .getConnection(); PreparedStatement preparedStatement = c
                .prepareStatement(
                        "DELETE FROM status_comment WHERE id = ?;");) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException | IOException e) {
            logger.error(e);
        }
    }

    @Override public void update(StatusComment statusComment) {
        try (Connection c = ConnectionManager.getInstance()
                .getConnection(); PreparedStatement preparedStatement = c
                .prepareStatement("UPDATE status_comment"
                        + " SET user_id = ?, item_id = ?, comment = ?, comment_date = ? WHERE id = ?;");) {
            preparedStatement.setLong(1, statusComment.getUserId());
            preparedStatement.setLong(2, statusComment.getItemId());
            preparedStatement.setString(3, statusComment.getComment());
            preparedStatement.setDate(4,
                    convertUtilToSql(statusComment.getCommentDate()));
            preparedStatement.setLong(5, statusComment.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException | IOException e) {
            logger.error(e);
        }
    }
}
