package com.example.moneycoach.controller;

import com.example.moneycoach.entity.Exit;
import com.example.moneycoach.service.ExitService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class ExitController {
    @Autowired
    ExitService exitService;

    @PostMapping("/addExit")
    public ResponseEntity<Void> createEntry(@Valid @RequestBody Exit exit){
        try{
            this.exitService.create(exit);
        }catch (Exception e){
            log.error("Something was wrong");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @GetMapping("/allExits")
    public ResponseEntity <List<Exit>> getAllEntries() {
        List<Exit> list = exitService.getTable();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/getExit{id}")
    public  ResponseEntity<Exit> getEntry (@PathVariable("id") long id){
        Exit entry;
        try{
            entry = this.exitService.getById(id);
        }
        catch (Exception e){
            log.error("Something was wrong, the user doesn't exists");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(entry, HttpStatus.OK);
    }

    @DeleteMapping("/deleteExit/{id}")
    public void deleteEntry (@PathVariable("id") Integer id){
        this.exitService.delete(id);
    }
}
