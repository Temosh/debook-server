package com.summ.debook.web;

import com.summ.debook.entity.PersonEntity;
import com.summ.debook.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Serhii Tymoshenko
 */
@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @RequestMapping(method = RequestMethod.GET)
    public List<PersonEntity> getPersons() {
        return personService.getPersons();
    }

    @RequestMapping(method = RequestMethod.POST)
    public void createPersons(@RequestBody PersonEntity person) {
        personService.createPerson(person);
    }
}
