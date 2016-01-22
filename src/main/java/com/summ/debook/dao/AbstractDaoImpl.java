package com.summ.debook.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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
    protected Transaction tx;

    public AbstractDaoImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Override
    public void create(T entity) {
        session = getSessionFactory().openSession();
        tx = session.beginTransaction();
        session.save(entity);
        tx.commit();
        session.close();
    }

    @Override
    public void edit(T entity) {
        session = getSessionFactory().openSession();
        tx = session.beginTransaction();
        session.update(entity);
        tx.commit();
        session.close();
    }

    @Override
    public void remove(T entity) {
        session = getSessionFactory().openSession();
        tx = session.beginTransaction();
        session.delete(entity);
        tx.commit();
        session.close();
    }

    @Override
    public T find(Serializable id) {
        session = getSessionFactory().openSession();
        tx = session.beginTransaction();
        T entity = session.get(entityClass, id);
        tx.commit();
        session.close();
        return entity;
    }

    @Override
    public List<T> findAll() {
        session = getSessionFactory().openSession();
        tx = session.beginTransaction();
        @SuppressWarnings("unchecked")
        List<T> entities = session.createCriteria(entityClass).list();
        tx.commit();
        session.close();
        return entities;
    }
}
