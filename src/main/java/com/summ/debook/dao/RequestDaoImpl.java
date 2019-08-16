package com.summ.debook.dao;

import com.summ.debook.entity.RequestEntity;
import com.summ.debook.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * @author Serhii Tymoshenko
 */
@Repository
public class RequestDaoImpl extends AbstractDaoImpl<RequestEntity> implements RequestDao {

    public RequestDaoImpl() {
        super(RequestEntity.class);
    }

    @Override
    public List<RequestEntity> findAllPendingRequest() {
        return em.createQuery("select r from RequestEntity r where r.processed = false", RequestEntity.class)
                .getResultList();
    }

    @Override
    public RequestEntity findPendingRequest(Serializable id) {
        List<RequestEntity> result = em.createQuery("select r from RequestEntity r where r.id = :id and r.processed = false", RequestEntity.class)
                .setParameter("id", id)
                .getResultList();
        if (result.isEmpty()) return null;
        return result.get(0);
    }

    @Override
    public RequestEntity findByPerson(UserEntity person) {
        return null;
    }
}
