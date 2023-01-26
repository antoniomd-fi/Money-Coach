package com.example.moneycoach.controller;

import com.example.moneycoach.entity.Entry;
import com.example.moneycoach.service.EntryService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class EntryController {

    @Autowired
    public EntryService entryService;
    @PostMapping("/addEntry")
    public ResponseEntity<Void> createEntry(@Valid @RequestBody Entry entry){
        try{
            this.entryService.create(entry);
        }catch (Exception e){
            log.error("Something was wrong");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @GetMapping("/allEntries")
    public ResponseEntity <List<Entry>> getAllEntries() {
        List<Entry> list = entryService.getTable();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/getEntry/{id}")
    public  ResponseEntity<Entry> getEntry (@PathVariable("id") long id){
        Entry entry;
        try{
            entry = this.entryService.getById(id);
        }
        catch (Exception e){
            log.error("Something was wrong, the user doesn't exists");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(entry, HttpStatus.OK);
    }

    @DeleteMapping("/deleteEntry/{id}")
    public void deleteEntry (@PathVariable("id") Integer id){
        this.entryService.delete(id);
    }
}
