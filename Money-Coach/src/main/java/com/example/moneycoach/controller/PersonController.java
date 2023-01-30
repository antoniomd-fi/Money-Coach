package com.example.moneycoach.controller;

import com.example.moneycoach.entity.Person;
import com.example.moneycoach.service.EntryService;
import com.example.moneycoach.service.ExitService;
import com.example.moneycoach.service.PersonService;
import com.example.moneycoach.service.ProducerService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;


@Slf4j
@RestController
public class PersonController {

    @Autowired
    public PersonService personService;
    @Autowired
    private EntryService entryService;
    @Autowired
    private ExitService exitService;

    @Autowired
    private ProducerService producerService;
    @PostMapping("/admin/addUser")
    public ResponseEntity<?>createUser(@Valid @RequestBody Person person){
        Person person1;
        try{
            person1 = this.personService.create(person);
        }catch (Exception e){
            log.error("Something was wrong");
            throw  new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Error");
        }
        return new ResponseEntity<>( person1, HttpStatus.OK);
    }

    @GetMapping("/allUsers")
    public ResponseEntity <List<Person>> getAllPerson() {
           List<Person> list = personService.getTable();
           return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/getUser/{id}")
    public  ResponseEntity<?> getPerson (@PathVariable("id") long id){
        Person person;
        try{
            person = this.personService.getById(id);
        }
        catch (Exception e){
            log.error("Something was wrong, the user doesn't exists");
            throw  new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Error");
        }

        if (person == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User doesn't exists in database");
        }
        else{
            return  ResponseEntity.ok(person);
        }
    }

    @DeleteMapping("/admin/deleteUser/{id}")
    public void deletePerson (@PathVariable("id") long id){
        this.personService.delete(id);
    }

    @GetMapping("/getBalance/{id}")
    public Double getBalance(@PathVariable("id") long id){
        Double balance = 0.0;

        if (personService.getById(id) == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User doesn't exists in database");
        }
        try {
            balance = entryService.getTotalAmountByUser(id) - exitService.getTotalAmountByUser(id);
        } catch (Exception e){
            log.error("Something was wrong, the user doesn't exists");
            throw  new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Error");
        }

        return balance;
    }

    @GetMapping("/sendList")
    public void sendList () {
        List<Person> list = personService.getTable();
        producerService.sendToRabbit(list);
    }
}
