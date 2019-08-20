package com.summ.debook.web;

import com.summ.debook.entity.PersonEntity;
import com.summ.debook.service.PersonService;
import com.summ.debook.service.converter.IdConversionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * @author Serhii Tymoshenko
 */
@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping
    public PersonEntity createPersons(@RequestBody PersonEntity person) {
        return personService.createPerson(person);
    }

    @GetMapping("/{personId}")
    public PersonEntity getPerson(@PathVariable String personId) {
        PersonEntity personEntity = personService.getPerson(IdConversionHelper.parseId(personId, "Wrong person ID format"));
        if (personEntity == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Person with id '" + personId + "' not found");
        }
        return personEntity;
    }

    @GetMapping
    public List<PersonEntity> getPersons() {
        return personService.getPersons();
    }

    @PutMapping("/{personId}")
    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    public void updatePerson(@PathVariable String personId) {

    }

    @DeleteMapping("/{personId}")
    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    public void deletePerson(@PathVariable String personId) {

    }
}
