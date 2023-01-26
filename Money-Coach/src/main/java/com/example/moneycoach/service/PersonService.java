package com.example.moneycoach.service;

import com.example.moneycoach.entity.Person;
import com.example.moneycoach.repository.PersonRepository;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@Slf4j
@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public void create (@RequestBody Person addedPerson){
        personRepository.save(addedPerson);
        log.info("User Added Successfully");
    }
    public Person getById(long id) {
        return  this.personRepository.findById(id).get();
    }

    public List<Person> getTable(){
        return personRepository.findAll(Sort.by("id").ascending());
    }

    public Person update(Integer id, String name, String lastname){
        Person person = getById(id);
        person.setName(name);
        person.setLastname(lastname);
        personRepository.save(person);
        log.info("User Updated Successfully");
        return person;
    }
    public void delete (long id){
        Person person = getById(id);
        personRepository.delete(person);
        log.warn("User Deleted on Person table");
    }

}
