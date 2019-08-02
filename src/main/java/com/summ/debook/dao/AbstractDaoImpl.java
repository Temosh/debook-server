package com.summ.debook.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
    public Serializable create(T entity) {
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
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(entityClass);
        Root<T> rootEntry = cq.from(entityClass);
        CriteriaQuery<T> all = cq.select(rootEntry);
        TypedQuery<T> allQuery = em.createQuery(all);
        return allQuery.getResultList();
    }
}
