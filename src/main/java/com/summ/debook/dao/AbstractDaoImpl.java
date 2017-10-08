package com.summ.debook.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

/**
 * @author Serhii Tymoshenko
 */
public abstract class AbstractDaoImpl<T> implements AbstractDao<T> {

    private Class<T> entityClass;

    @Autowired
    private SessionFactory sessionFactory;

    protected Session session;

    public AbstractDaoImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Override
    public void create(T entity) {
        session = getSessionFactory().getCurrentSession();
        session.save(entity);
    }

    @Override
    public void edit(T entity) {
        session = getSessionFactory().getCurrentSession();
        session.update(entity);
    }

    @Override
    public void remove(T entity) {
        session = getSessionFactory().getCurrentSession();
        session.delete(entity);
    }

    @Override
    public T find(Serializable id) {
        session = getSessionFactory().getCurrentSession();
        T entity = session.get(entityClass, id);
        return entity;
    }

    @Override
    public List<T> findAll() {
        session = getSessionFactory().getCurrentSession();
        @SuppressWarnings("unchecked")
        List<T> entities = session.createCriteria(entityClass).list();
        return entities;
    }
}
