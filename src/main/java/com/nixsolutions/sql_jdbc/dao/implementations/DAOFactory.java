package com.nixsolutions.sql_jdbc.dao.implementations;

import com.nixsolutions.sql_jdbc.dao.interfaces.IDAOFactory;

public class DAOFactory implements IDAOFactory {

    private static IDAOFactory factory;

    private DAOFactory() {
    }

    public static synchronized IDAOFactory getInstance() {
        if (factory == null) {
            factory = new DAOFactory();
        }
        return factory;
    }

    @Override public AuthorDAOImpl getAuthorDAO() {
        return new AuthorDAOImpl();
    }

    @Override public BookDAOImpl getBookDAO() {
        return new BookDAOImpl();
    }

    @Override public BookAuthorDAOImpl getBookAuthorDAO() {
        return new BookAuthorDAOImpl();
    }

    @Override public BookGenreDAOImpl getBookGenreDAO() {
        return new BookGenreDAOImpl();
    }

    @Override public CompositionCommentDAOImpl getCompositionCommentDAO() {
        return new CompositionCommentDAOImpl();
    }

    @Override public GenreDAOImpl getGenreDAO() {
        return new GenreDAOImpl();
    }

    @Override public ItemDAOImpl getItemDAO() {
        return new ItemDAOImpl();
    }

    @Override public ItemStatusDAOImpl getItemStatusDAO() {
        return new ItemStatusDAOImpl();
    }

    @Override public OrderStatusDAOImpl getOrderStatusDAO() {
        return new OrderStatusDAOImpl();
    }

    @Override public StatusCommentDAOImpl getStatusCommentDAO() {
        return new StatusCommentDAOImpl();
    }

    @Override public SystemUserDAOImpl getSystemUserDAO() {
        return new SystemUserDAOImpl();
    }

    @Override public UserLoginDAOImpl getUserLoginDAO() {
        return new UserLoginDAOImpl();
    }

    @Override public UserOrderDAOImpl getUserOrderDAO() {
        return new UserOrderDAOImpl();
    }

    @Override public UserRoleDAOImpl getUserRoleDAO() {
        return new UserRoleDAOImpl();
    }
}
