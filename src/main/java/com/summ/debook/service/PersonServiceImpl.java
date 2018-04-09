package com.summ.debook.service;

import com.summ.debook.entity.PersonEntity;
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
    private UserService userService;

    @Transactional
    @Override
    public List<PersonEntity> getPersons(String login) {
        return userService.getUser(login).getPersons();
    }
}
