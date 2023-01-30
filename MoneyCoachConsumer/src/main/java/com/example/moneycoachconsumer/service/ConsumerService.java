package com.example.moneycoachconsumer.service;

import com.example.moneycoachconsumer.model.Person;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ConsumerService {

    @Getter public List<Person> personaList;
    public void saveRecivedList(List<Person> personList){
        this.personaList = personList;
    }
}
