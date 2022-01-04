package io.xmljim.spring.starter.service;

import io.xmljim.spring.starter.domain.Person;
import io.xmljim.spring.starter.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository repository) {
        this.personRepository = repository;
    }

    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    public Person getPersonById(int id) {
        return personRepository.getById(id);
    }

    public Person addPerson(Person person) {
        return personRepository.save(person);
    }

    public void deletePersonById(int id) {
        personRepository.deleteById(id);
    }

    public Person updatePerson(Person person) {
        return personRepository.save(person);
    }

}
