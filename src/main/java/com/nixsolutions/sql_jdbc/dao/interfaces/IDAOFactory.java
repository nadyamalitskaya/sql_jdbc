package com.nixsolutions.sql_jdbc.dao.interfaces;

import com.nixsolutions.sql_jdbc.dao.implementations.*;

public interface IDAOFactory {
    AuthorDAOImpl getAuthorDAO();

    BookDAOImpl getBookDAO();

    BookAuthorDAOImpl getBookAuthorDAO();

    BookGenreDAOImpl getBookGenreDAO();

    CompositionCommentDAOImpl getCompositionCommentDAO();

    GenreDAOImpl getGenreDAO();

    ItemDAOImpl getItemDAO();

    ItemStatusDAOImpl getItemStatusDAO();

    OrderStatusDAOImpl getOrderStatusDAO();

    StatusCommentDAOImpl getStatusCommentDAO();

    SystemUserDAOImpl getSystemUserDAO();

    UserLoginDAOImpl getUserLoginDAO();

    UserOrderDAOImpl getUserOrderDAO();

    UserRoleDAOImpl getUserRoleDAO();
}
