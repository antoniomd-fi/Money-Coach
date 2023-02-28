package com.example.moneycoach.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import com.example.moneycoach.controller.EntryController;
import com.example.moneycoach.entity.Entry;
import com.example.moneycoach.repository.EntryRepository;
import com.example.moneycoach.service.EntryService;
import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class EntryServiceTest {

    @Mock
    private EntryRepository entryRepository;

    @InjectMocks
    private EntryService entryService;
    @BeforeAll()
    static  void initAll(){log.info("Init all entry tests");}

    @AfterEach
    void tearDown(){log.info("tearDown");}

    @AfterAll
    static void tearDownAll(){log.info("tearDownAll entry tests");}

    Entry entry = new Entry();
    List<Entry> list = new ArrayList<>();

    @BeforeEach
    void variable(){
        entry.setId(1L);
        entry.setAmount(10.00);
        entry.setConcept("test");
        entry.setDate("2022-12-30");
        entry.setPerson_id(1L);
        list.add(entry);
    }
    @Test
    void addAnEntry(){
        when(entryRepository.save(ArgumentMatchers.any()))
                .thenReturn(entry);

        assertNotNull(entryService.create(entry));
    }

    @ParameterizedTest
    @ValueSource (longs = {1,2,3,4,5})
    void addManyEntries(long id){
        entry.setId(id);
        entry.setAmount(10.00);
        entry.setConcept("test");
        entry.setDate("2022-12-30");
        entry.setPerson_id(1);

        when(entryRepository.save(ArgumentMatchers.any()))
                .thenReturn(entry);
        assertNotNull(entryService.create(entry));
    }

    @ParameterizedTest
    @ValueSource (longs = {1,2,3,4,5})
    void getById(long id){
        entry.setId(id);
        entry.setAmount(50.00);
        entry.setConcept("test");
        entry.setDate("2022-12-30");
        entry.setPerson_id(1);

        Mockito.when(entryRepository.findById(ArgumentMatchers.anyLong()))
                        .thenReturn(Optional.of(entry));

        System.out.println(entryService.getById(id));
        assertNotNull(entryService.getById(id));

    }

    @Test
    void getAll(){
        for (long i = 2; i <= 5; i++){
            entry.setId(i);
            list.add(entry);
        }


        Mockito.when(entryRepository.findAll(any(Sort.class)))
                .thenReturn(list);

        assertNotNull(entryService.getTable());
        assertEquals(5, entryService.getTable().size());
    }

    @ParameterizedTest
    @ValueSource(longs = {2,3,4,5,6,7,8,9})
    void getTableByDiferentsSize(long size){
        for (long i = 2; i <= size; i++){
            entry.setId(i);
            list.add(entry);
        }


        Mockito.when(entryRepository.findAll(any(Sort.class)))
                .thenReturn(list);

        assertNotNull(entryService.getTable());
        assertEquals(size, entryService.getTable().size());
    }

    @Test
    void GetTotalAmount(){
        for (long i = 2; i <= 5; i++){
            entry.setId(i);
            list.add(entry);
        }
        Mockito.when(entryRepository.findAll(any(Sort.class)))
                .thenReturn(list);

        assertNotNull(entryService.getTotalAmountByUser(1L));
        assertEquals(50.00, entryService.getTotalAmountByUser(1L));
    }

}