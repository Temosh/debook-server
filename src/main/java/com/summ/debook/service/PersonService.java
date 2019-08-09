package com.summ.debook.service;

import com.summ.debook.entity.PersonEntity;

import java.util.List;

/**
 * @author Serhii Tymoshenko
 */
public interface PersonService {
    List<PersonEntity> getPersons();
    PersonEntity getPerson(long personId);
    void createPerson(PersonEntity person);
}
