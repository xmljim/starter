package io.xmljim.spring.starter.controller;

import io.xmljim.spring.starter.domain.Person;
import io.xmljim.spring.starter.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/{id}")
    public Person findById(@PathVariable int id) {
        return personService.getPersonById(id);
    }

    @GetMapping
    public List<Person> findAll() {
        return personService.getAllPersons();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        personService.deletePersonById(id);
    }

    @PutMapping
    public Person update(@RequestBody Person person) {
        return personService.updatePerson(person);
    }

    @PostMapping
    public Person add(@RequestBody Person person) {
        return personService.addPerson(person);
    }

}
