package com.summ.debook.dao;

import com.summ.debook.entity.RequestEntity;
import com.summ.debook.entity.UserEntity;

import java.io.Serializable;
import java.util.List;

/**
 * @author Serhii Tymoshenko
 */
public interface RequestDao extends AbstractDao<RequestEntity> {
    List<RequestEntity> findAll(UserEntity userEntity);
    List<RequestEntity> findAllPendingRequest(UserEntity userEntity);

    RequestEntity findPendingRequest(Serializable id);
    RequestEntity findPendingRequestByConnection(UserEntity user1, UserEntity user2);
}
