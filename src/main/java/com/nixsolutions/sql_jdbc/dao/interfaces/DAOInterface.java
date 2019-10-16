package com.nixsolutions.sql_jdbc.dao.interfaces;

import java.util.List;

public interface DAOInterface<T> {
    void add(T t);

    List<T> getAll();

    T getById(int id);

    void delete(int id);

    void update(T t);
}
