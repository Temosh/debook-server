package com.summ.debook.web;

import com.summ.debook.entity.PersonEntity;
import com.summ.debook.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    public void createPersons(@RequestBody PersonEntity person) {
        personService.createPerson(person);
    }

    @GetMapping("/personId")
    public PersonEntity getPerson(@PathVariable String personId) {
        return personService.getPerson(Long.parseLong(personId)); //TODO Number parsing!!!
    }

    @GetMapping
    public List<PersonEntity> getPersons() {
        return personService.getPersons();
    }

    @PutMapping("/personId")
    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    public void updatePerson(@PathVariable String personId) {

    }

    @DeleteMapping("/personId")
    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    public void deletePerson(@PathVariable String personId) {

    }
}
