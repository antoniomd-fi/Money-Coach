package com.example.moneycoach.service;

import com.example.moneycoach.entity.Entry;
import com.example.moneycoach.repository.EntryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class EntryService {

    @Autowired
    private EntryRepository entryRepository;

    public Entry create (@RequestBody Entry addedEntry){
        log.info("Entry Added Successfully");
       return entryRepository.save(addedEntry);
    }
    public Entry getById(long id) {
        if (entryRepository.existsById(id)){
            return this.entryRepository.findById(id).get();
        }
        return null;
    }

    public List<Entry> getTable(){
        return entryRepository.findAll(Sort.by("id").ascending());
    }

    public Entry update(Integer id, String concept, Double amount){
        Entry entry = getById(id);
        entry.setConcept(concept);
        entry.setAmount(amount);
        entryRepository.save(entry);
        log.info("Entry Updated Successfully");
        return entry;
    }
    public void delete (long id){
        Entry entry = getById(id);
        entryRepository.delete(entry);
        log.warn("Entry Deleted");
    }

    public Double getTotalAmountByUser(long id){

        List<Entry> entries = entryRepository.findAll();
        Double total = 0.0;
        for (int i = 0; i<entries.size(); i ++){
            if (entries.get(i).getPerson_id() == id){
                total += entries.get(i).getAmount();
            }
        }

        return total;
    }

    public List<Entry> getEntriesByUser(long id){
        List<Entry> entries = entryRepository.findAll();
        List<Entry> filterEntries = new ArrayList<>();

        for (int i = 0; i<entries.size(); i ++){
            if (entries.get(i).getPerson_id() == id){
                filterEntries.add(entries.get(i));
            }
        }

        return  filterEntries;
    }
}
