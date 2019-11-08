package com.bubnii.model.dao.interfaces;

import java.util.List;

public interface GeneralDAO<T, ID> {
    T findByID(ID id);

    void save(T entity);

    void update(T entity);

    void delete(T entity);

    List<T> findAll();
}
