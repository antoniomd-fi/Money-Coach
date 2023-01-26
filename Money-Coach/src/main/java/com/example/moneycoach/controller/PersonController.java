package com.example.moneycoach.controller;

import com.example.moneycoach.entity.Person;
import com.example.moneycoach.service.PersonService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;


@Slf4j
@RestController
public class PersonController {

    @Autowired
    public PersonService personService;
    @PostMapping("/addUser")
    public ResponseEntity<Void>createUser(@Valid @RequestBody Person person){
        try{
            this.personService.create(person);
        }catch (Exception e){
            log.error("Something was wrong");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @GetMapping("/allUsers")
    public ResponseEntity <List<Person>> getAllPerson() {
           List<Person> list = personService.getTable();
           return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/getUser/{id}")
    public  ResponseEntity<Person> getPerson (@PathVariable("id") long id){
        Person person;
        try{
            person = this.personService.getById(id);
        }
        catch (Exception e){
            log.error("Something was wrong, the user doesn't exists");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @DeleteMapping("/deleteUser/{id}")
    public void deletePerson (@PathVariable("id") Integer id){
        this.personService.delete(id);
    }
}
