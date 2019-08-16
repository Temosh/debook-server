package com.summ.debook.service;

import com.summ.debook.dao.PersonDao;
import com.summ.debook.entity.PersonEntity;
import com.summ.debook.entity.UserEntity;
import com.summ.debook.service.converter.IdConversionHelper;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.NoResultException;
import java.util.List;

/**
 * @author Serhii Tymoshenko
 */
@Component
public class PersonServiceImpl implements PersonService {

    private static Logger LOG = LoggerFactory.getLogger(PersonServiceImpl.class);

    @Autowired
    private PersonDao personDao;

    @Autowired
    private UserService userService;

    @Override
    @Transactional
    public List<PersonEntity> getPersons() {
        UserEntity userEntity = userService.getUser(userService.getCurrentUser().getUserId());
        List<PersonEntity> personList = userEntity.getPersons();
        personList.forEach(person -> Hibernate.initialize(person.getDebts()));
        return personList;
    }

    @Override
    public List<PersonEntity> getPersonsByConnection(UserEntity user1, UserEntity user2) {
        return personDao.findByConnection(user1, user2);
    }

    @Override
    @Transactional
    public PersonEntity getPerson(long personId) {
        UserEntity user = userService.getCurrentUser();
        PersonEntity person = personDao.find(personId);

        //TODO Throw proper exception
        if (!person.getOwnerUser().getUserId().equals(user.getUserId())) {
            throw new IllegalArgumentException();
        }

        Hibernate.initialize(person.getDebts());

        return person;
    }

    @Override
    @Transactional
    public PersonEntity getPersonByConnectedUser(UserEntity connectedUser) {
        UserEntity ownerUser = userService.getCurrentUser();
        return getPersonByConnection(ownerUser, connectedUser);
    }

    private PersonEntity getPersonByConnection(UserEntity ownerUser, UserEntity connectedUser) {
        return personDao.findByConnectedUser(ownerUser, connectedUser);
    }

    @Override
    @Transactional
    public void createPerson(PersonEntity person) {
        person.setOwnerUser(userService.getCurrentUser());
        personDao.create(person);
    }

    @Override
    @Transactional
    public void connectUser(String personId, UserEntity userEntity) {
        Long personIdLong = IdConversionHelper.parseId(personId, "Wrong person ID format");

        //TODO Create database unique constraint
        PersonEntity connectedPersonEntity = getPersonByConnectedUser(userEntity);
        if (connectedPersonEntity != null) {
            if (personId.equals(connectedPersonEntity.getPersonId().toString())) {
                if (connectedPersonEntity.isConnectionApproved() != null && connectedPersonEntity.isConnectionApproved()) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Connection already approved for user " + userEntity);
                } else {
                    //User already linked, waiting for connection request to be approved
                    return;
                }
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Current user already has connection to user " + userEntity);
            }
        }

        PersonEntity personEntity = getPerson(personIdLong);

        if (personEntity == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Person with ID " + personIdLong + " not found");
        }

        personEntity.setConnectedUser(userEntity);
    }

    @Override
    @Transactional
    public void approveConnection(UserEntity user1, UserEntity user2) {
        approvePersonConnection(user1, user2);
        approvePersonConnection(user2, user1);
    }

    @Override
    @Transactional
    public void removeConnection(UserEntity user1, UserEntity user2) {
        PersonEntity person;

        person = getPersonByConnection(user1, user2);
        if (person != null) {
            person.setConnectedUser(null);
            person.setConnectionApproved(false);
        }

        person = getPersonByConnection(user2, user1);
        if (person != null) {
            person.setConnectedUser(null);
            person.setConnectionApproved(false);
        }
    }

    private void approvePersonConnection(UserEntity ownerUser, UserEntity connectedUser) {
        PersonEntity person = getPersonByConnection(ownerUser, connectedUser);
        if (person == null) {
            person = new PersonEntity();
            person.setOwnerUser(ownerUser);
            person.setConnectedUser(connectedUser);
            person.setName(connectedUser.getLogin());
        }
        person.setConnectionApproved(true);

        if (person.getPersonId() == null) {
            personDao.create(person);
        }
    }
}
