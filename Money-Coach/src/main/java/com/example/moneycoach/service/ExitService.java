package com.example.moneycoach.service;

import com.example.moneycoach.entity.Entry;
import com.example.moneycoach.entity.Exit;
import com.example.moneycoach.repository.ExitRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Slf4j
@Service


public class ExitService {

    @Autowired
    private ExitRepository exitRepository;

    public void create (@RequestBody Exit addedExit){
        exitRepository.save(addedExit);
        log.info("Exit Added Successfully");
    }
    public Exit getById(long id) {
        return this.exitRepository.findById(id).get();
    }

    public List<Exit> getTable(){
        return exitRepository.findAll(Sort.by("id").ascending());
    }

    public Exit update(Integer id, String concept, long amount){
        Exit exit = getById(id);
        exit.setConcept(concept);
        exit.setAmount(amount);
        exitRepository.save(exit);
        log.info("Exit Updated Successfully");
        return exit;
    }
    public void delete (long id){
        Exit exit = getById(id);
        exitRepository.delete(exit);
        log.warn("Exit Deleted");
    }
}
