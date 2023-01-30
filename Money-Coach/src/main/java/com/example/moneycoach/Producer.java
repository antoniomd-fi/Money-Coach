package com.example.moneycoach;
import com.example.moneycoach.entity.Person;
import com.example.moneycoach.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@EnableRabbit
public class Producer {

    @Autowired
    PersonService personService;
    @Value("${moneyCoach.exchange}")
    private String TOPIC_EXCHANGE_NAME;

    @Value("${moneyCoach.routing}")
    private String ROUTING_KEY;

    private RabbitTemplate template;

    @Autowired
    public Producer(RabbitTemplate template){
        this.template = template;
    }

    public void produce(List<Person> personList) {

        template.convertAndSend(TOPIC_EXCHANGE_NAME, ROUTING_KEY, personList);
    }
}
