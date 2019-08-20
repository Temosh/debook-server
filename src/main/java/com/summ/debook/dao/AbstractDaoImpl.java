package com.summ.debook.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;

/**
 * @author Serhii Tymoshenko
 */
public abstract class AbstractDaoImpl<T extends Serializable> implements AbstractDao<T> {

    private Class<T> entityClass;

    @PersistenceContext
    protected EntityManager em;

    public AbstractDaoImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public T create(T entity) {
        em.persist(entity);
        return entity;
    }

    @Override
    public void remove(T entity) {
        em.remove(entity);
    }

    @Override
    public T find(Serializable id) {
        return em.find(entityClass, id);
    }

    @Override
    public List<T> findAll() {
        CriteriaQuery<T> criteria = em.getCriteriaBuilder().createQuery(entityClass);
        criteria.select(criteria.from(entityClass));
        return em.createQuery(criteria).getResultList();
    }

    @Override
    public T getReference(Serializable id) {
        return em.getReference(entityClass, id);
    }
}
