package com.example.moneycoach.controller;

import com.example.moneycoach.entity.Exit;
import com.example.moneycoach.service.ExitService;
import com.example.moneycoach.service.PersonService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Slf4j
@RestController
public class ExitController {
    @Autowired
    private ExitService exitService;
    @Autowired
    private PersonService personService;

    @PostMapping("/addExit")
    public ResponseEntity<?> createEntry(@Valid @RequestBody Exit exit){
        Exit exit1;
        try{
           exit1 = this.exitService.create(exit);
        }catch (Exception e){
            log.error("Something was wrong");
            throw  new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Error");
        }
        return new ResponseEntity<>(exit1, HttpStatus.OK);
    }

    @GetMapping("/allExits")
    public ResponseEntity <List<Exit>> getAllEntries() {
        List<Exit> list = exitService.getTable();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/getExit/{id}")
    public  ResponseEntity<?> getEntry (@PathVariable("id") long id){
        Exit exit;
        try{
            exit = this.exitService.getById(id);
        }
        catch (Exception e){
            log.error("Something was wrong, the user doesn't exists");
            throw  new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Error");
        }
        if (exit == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entry doesn't exists in database");
        }
        else{
            return  ResponseEntity.ok(exit);
        }

    }

    @DeleteMapping("/deleteExit/{id}")
    public void deleteEntry (@PathVariable("id") Integer id){
        this.exitService.delete(id);
    }

    @GetMapping("/getTotalExitsByUser/{id}")
    public ResponseEntity<?> getTotalAmount (@PathVariable("id") long id){
        Double total = 0.0;
        if (personService.getById(id)==null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User doesn't exists in database");
        }
        try {
            total = exitService.getTotalAmountByUser(id);
        }
        catch (Exception e){
            log.error("Something was wrong, the user doesn't exists");
            throw  new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Error");
        }
        return  ResponseEntity.ok(total);
    }
}
