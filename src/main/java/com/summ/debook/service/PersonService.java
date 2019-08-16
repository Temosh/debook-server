package com.summ.debook.service;

import com.summ.debook.entity.PersonEntity;
import com.summ.debook.entity.UserEntity;

import java.util.List;

/**
 * @author Serhii Tymoshenko
 */
public interface PersonService {
    List<PersonEntity> getPersons();
    List<PersonEntity> getPersonsByConnection(UserEntity user1, UserEntity user2);
    PersonEntity getPerson(long personId);
    PersonEntity getPersonByConnectedUser(UserEntity targetUser);
    void createPerson(PersonEntity person);

    void connectUser(String personId, UserEntity userEntity);
    void approveConnection(UserEntity user1, UserEntity user2);
    void removeConnection(UserEntity user1, UserEntity user2);
}
