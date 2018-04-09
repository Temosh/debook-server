package com.summ.debook.service;

import com.summ.debook.dao.PersonDao;
import com.summ.debook.entity.PersonEntity;
import com.summ.debook.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Serhii Tymoshenko
 */
@Component
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonDao personDao;

    @Autowired
    private UserService userService;

    @Transactional
    @Override
    public List<PersonEntity> getPersons() {
        return userService.getCurrentUser().getPersons();
    }

    @Transactional
    @Override
    public void createPerson(PersonEntity person) {
        person.setOwnerUser(userService.getCurrentUser());
        personDao.create(person);
    }
}
