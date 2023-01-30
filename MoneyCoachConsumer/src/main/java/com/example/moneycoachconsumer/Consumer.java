package com.example.moneycoachconsumer;


import com.example.moneycoachconsumer.model.Person;
import com.example.moneycoachconsumer.service.ConsumerService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Slf4j
@Component
public class Consumer {
    @Autowired
    ConsumerService consumerService;
    @RabbitListener (queues = {"${moneyCoach.queue}"})
    public void consume (List<Person> personList){
        log.info("Message Received...");
        consumerService.saveRecivedList(personList);
    }
}
