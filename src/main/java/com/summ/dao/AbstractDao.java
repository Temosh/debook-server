package com.summ.dao;

import java.io.Serializable;
import java.util.List;

/**
 * @author Serhii Tymoshenko
 */
public interface AbstractDao<T> {
    void create(T entity);
    void edit(T entity);
    void remove(T entity);
    T find(Serializable id);
    List<T> findAll();
}
