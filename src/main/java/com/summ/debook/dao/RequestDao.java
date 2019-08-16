package com.summ.debook.dao;

import com.summ.debook.entity.RequestEntity;
import com.summ.debook.entity.UserEntity;

import java.io.Serializable;
import java.util.List;

/**
 * @author Serhii Tymoshenko
 */
public interface RequestDao extends AbstractDao<RequestEntity> {
    List<RequestEntity> findAllPendingRequest();

    RequestEntity findPendingRequest(Serializable id);
    RequestEntity findByPerson(UserEntity person);
}
