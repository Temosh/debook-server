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
    public List<RequestEntity> findAll(UserEntity userEntity) {
        return em.createQuery(
                "select r from RequestEntity r " +
                        "where r.sourceUser = :userEntity or r.targetUser = :userEntity",
                RequestEntity.class)
                .setParameter("userEntity", userEntity)
                .getResultList();
    }

    @Override
    public List<RequestEntity> findAllPendingRequest(UserEntity userEntity) {
        return em.createQuery(
                "select r from RequestEntity r " +
                        "where (r.sourceUser = :userEntity or r.targetUser = :userEntity) and r.processed = false",
                RequestEntity.class)
                .setParameter("userEntity", userEntity)
                .getResultList();
    }

    @Override
    public RequestEntity findPendingRequest(Serializable id) {
        List<RequestEntity> result = em.createQuery(
                "select r from RequestEntity r where r.id = :id and r.processed = false",
                RequestEntity.class)
                .setParameter("id", id)
                .getResultList();
        if (result.isEmpty()) return null;
        return result.get(0);
    }

    @Override
    public RequestEntity findPendingRequestByConnection(UserEntity user1, UserEntity user2) {
        List<RequestEntity> result = em.createQuery(
                "select r from RequestEntity r " +
                        "where r.processed = false " +
                        "and ((r.sourceUser = :user1 and r.targetUser = :user2)" +
                        "or (r.sourceUser = :user2 and r.targetUser = :user1))",
                RequestEntity.class)
                .setParameter("user1", user1)
                .setParameter("user2", user2)
                .getResultList();
        if (result.isEmpty()) return null;
        return result.get(0);
    }
}
