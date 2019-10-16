package com.nixsolutions.sql_jdbc.dao.implementations;

import com.nixsolutions.sql_jdbc.ConnectionManager;
import com.nixsolutions.sql_jdbc.dao.interfaces.DAOInterface;
import com.nixsolutions.sql_jdbc.entity.CompositionComment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompositionCommentDAOImpl
        implements DAOInterface<CompositionComment> {
    private static final Logger logger = LogManager
            .getLogger(CompositionCommentDAOImpl.class);

    @Override public void add(CompositionComment compositionComment) {
        try (Connection c = ConnectionManager.getInstance()
                .getConnection(); PreparedStatement preparedStatement = c
                .prepareStatement("INSERT INTO composition_comment "
                        + "(user_id, book_id, comment, comment_date)"
                        + " VALUES (?,?,?,?) RETURNING id;");) {
            preparedStatement.setLong(1, compositionComment.getUserId());
            preparedStatement.setLong(2, compositionComment.getBookId());
            preparedStatement.setString(3, compositionComment.getComment());
            preparedStatement.setDate(4,
                    convertUtilToSql(compositionComment.getCommentDate()));
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            Long id = resultSet.getLong("id");
            compositionComment.setId(id);
        } catch (SQLException | IOException e) {
            logger.error(e);
        }
    }

    private static java.sql.Date convertUtilToSql(java.util.Date uDate) {
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }

    @Override public List<CompositionComment> getAll() {
        List<CompositionComment> list = new ArrayList<>();
        try (Connection c = ConnectionManager.getInstance()
                .getConnection(); PreparedStatement preparedStatement = c
                .prepareStatement("SELECT * FROM composition_comment;");) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                CompositionComment compositionComment = new CompositionComment();
                compositionComment.setId((resultSet.getLong("id")));
                compositionComment.setUserId((resultSet.getLong("user_id")));
                compositionComment.setBookId((resultSet.getLong("book_id")));
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

    @Override public CompositionComment getById(int id) {
        CompositionComment compositionComment = new CompositionComment();
        try (Connection c = ConnectionManager.getInstance()
                .getConnection(); PreparedStatement preparedStatement = c
                .prepareStatement(
                        "SELECT * FROM composition_comment WHERE id = ?;");) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            compositionComment.setId((resultSet.getLong("id")));
            compositionComment.setUserId((resultSet.getLong("user_id")));
            compositionComment.setBookId((resultSet.getLong("book_id")));
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
                        "DELETE FROM composition_comment WHERE id = ?;");) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException | IOException e) {
            logger.error(e);
        }
    }

    @Override public void update(CompositionComment compositionComment) {
        try (Connection c = ConnectionManager.getInstance()
                .getConnection(); PreparedStatement preparedStatement = c
                .prepareStatement("UPDATE composition_comment"
                        + " SET user_id = ?, book_id = ?, comment = ?, comment_date = ? WHERE id = ?;");) {
            preparedStatement.setLong(1, compositionComment.getUserId());
            preparedStatement.setLong(2, compositionComment.getBookId());
            preparedStatement.setString(3, compositionComment.getComment());
            preparedStatement.setDate(4,
                    convertUtilToSql(compositionComment.getCommentDate()));
            preparedStatement.setLong(5, compositionComment.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException | IOException e) {
            logger.error(e);
        }
    }
}
