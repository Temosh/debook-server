package com.summ.debook.dao;

import com.summ.debook.entity.PersonEntity;
import com.summ.debook.entity.UserEntity;

import java.util.List;

/**
 * @author Serhii Tymoshenko
 */
public interface PersonDao extends AbstractDao<PersonEntity> {
    List<PersonEntity> findByUser(UserEntity user);
    List<PersonEntity> findByConnection(UserEntity user1, UserEntity user2);
    PersonEntity findByConnectedUser(UserEntity ownerUser, UserEntity connectedUserId);
}
