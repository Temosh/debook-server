package com.summ.debook.dao;

import java.io.Serializable;
import java.util.List;

/**
 * @author Serhii Tymoshenko
 */
public interface AbstractDao<T> {
    Serializable create(T entity);
    void edit(T entity);
    void remove(T entity);
    T find(Serializable id);
    List<T> findAll();
}
