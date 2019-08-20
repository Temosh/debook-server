package com.summ.debook.dao;

import java.io.Serializable;
import java.util.List;

/**
 * @author Serhii Tymoshenko
 */
public interface AbstractDao<T extends Serializable> {
    T create(T entity);
    void remove(T entity);
    T find(Serializable id);
    List<T> findAll();
    T getReference(Serializable id);
}
