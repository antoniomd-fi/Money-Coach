package com.example.moneycoach.service;

import com.example.moneycoach.entity.Entry;
import com.example.moneycoach.entity.Exit;
import com.example.moneycoach.repository.EntryRepository;
import com.example.moneycoach.repository.ExitRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@Slf4j
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ExitServiceTest {


    @Mock
    private ExitRepository exitRepository;

    @InjectMocks
    private ExitService exitService;
    @BeforeAll()
    static  void initAll(){log.info("Init all entry tests");}

    @AfterEach
    void tearDown(){log.info("tearDown");}

    @AfterAll
    static void tearDownAll(){log.info("tearDownAll entry tests");}

    Exit exit = new Exit();
    List<Exit> list = new ArrayList<>();

    @BeforeEach
    void variable(){
        exit.setId(1L);
        exit.setAmount(10.00);
        exit.setConcept("test");
        exit.setDate(LocalDateTime.of(2022,12,30,12,12,12));
        exit.setPersonId(1L);
        list.add(exit);
    }
    @Test
    void addAnEntry(){
        when(exitRepository.save(ArgumentMatchers.any()))
                .thenReturn(exit);

        assertNotNull(exitService.create(exit));
    }

    @ParameterizedTest
    @ValueSource(longs = {1,2,3,4,5})
    void addManyEntries(long id){
        exit.setId(id);
        exit.setAmount(10.00);
        exit.setConcept("test");
        exit.setDate(LocalDateTime.of(2022,12,30,12,12,12));
        exit.setPersonId(1);

        when(exitRepository.save(ArgumentMatchers.any()))
                .thenReturn(exit);
        assertNotNull(exitService.create(exit));
    }

    @ParameterizedTest
    @ValueSource (longs = {1,2,3,4,5})
    void getById(long id){
        exit.setId(id);
        exit.setAmount(50.00);
        exit.setConcept("test");
        exit.setDate(LocalDateTime.of(2022,12,30,12,12,12));
        exit.setPersonId(1);

        Mockito.when(exitRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(exit));

        System.out.println(exitService.getById(id));
        assertNotNull(exitService.getById(id));

    }

    @Test
    void getAll(){
        for (long i = 2; i <= 5; i++){
            exit.setId(i);
            list.add(exit);
        }


        Mockito.when(exitRepository.findAll(any(Sort.class)))
                .thenReturn(list);

        assertNotNull(exitService.getTable());
        assertEquals(5, exitService.getTable().size());
    }

    @ParameterizedTest
    @ValueSource(longs = {2,3,4,5,6,7,8,9})
    void getTableByDiferentsSize(long size){
        for (long i = 2; i <= size; i++){
            exit.setId(i);
            list.add(exit);
        }


        Mockito.when(exitRepository.findAll(any(Sort.class)))
                .thenReturn(list);

        assertNotNull(exitService.getTable());
        assertEquals(size, exitService.getTable().size());
    }

    @Test
    void GetTotalAmount(){
        for (long i = 2; i <= 5; i++){
            exit.setId(i);
            list.add(exit);
        }
        Mockito.when(exitRepository.findAll(any(Sort.class)))
                .thenReturn(list);

        assertNotNull(exitService.getTotalAmountByUser(1L));
        assertEquals(50.00, exitService.getTotalAmountByUser(1L));
    }

}