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

    public Exit create (@RequestBody Exit addedExit){

        log.info("Exit Added Successfully");
        return exitRepository.save(addedExit);
    }
    public Exit getById(long id) {
        if (exitRepository.existsById(id)){
            return this.exitRepository.findById(id).get();
        }
        return null;
    }

    public List<Exit> getTable(){
        return exitRepository.findAll(Sort.by("id").ascending());
    }

    public Exit update(Integer id, String concept, Double amount){
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

    public Double getTotalAmountByUser(long id){

        List<Exit> exits = exitRepository.findAll();
        Double total = 0.0;

        for (int i = 0; i<exits.size(); i ++){
            if (exits.get(i).getPersonId() == id){
                total += exits.get(i).getAmount();
            }
        }

        return total;
    }
}
