package com.example.moneycoach.service;

import com.example.moneycoach.entity.Exit;
import com.example.moneycoach.entity.Person;
import com.example.moneycoach.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@Slf4j
@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;

    @BeforeAll()
    static  void initAll(){log.info("Init all entry tests");}

    @AfterEach
    void tearDown(){log.info("tearDown");}

    @AfterAll
    static void tearDownAll(){log.info("tearDownAll entry tests");}

    Person person = new Person();
    List<Person> list = new ArrayList<>();

    @BeforeEach
    void variable(){
        person.setId(1L);
        person.setName("Test");
        person.setLastname("Test");
        person.setUsername("test-user");
        list.add(person);
    }

    @Test
    void addAnEntry(){
        when(personRepository.save(ArgumentMatchers.any()))
                .thenReturn(person);

        assertNotNull(personService.create(person));
    }

    @ParameterizedTest
    @ValueSource(longs = {1,2,3,4,5})
    void addManyEntries(long id){
        person.setId(id);
        person.setName("Test");
        person.setLastname("Test");
        person.setUsername("test-user");

        when(personRepository.save(ArgumentMatchers.any()))
                .thenReturn(person);
        assertNotNull(personService.create(person));
    }

    @Test
    void getAll(){
        for (long i = 2; i <= 5; i++){
            person.setId(i);
            list.add(person);
        }


        Mockito.when(personRepository.findAll(any(Sort.class)))
                .thenReturn(list);

        assertNotNull(personService.getTable());
        assertEquals(5, personService.getTable().size());
    }

    @ParameterizedTest
    @ValueSource(longs = {2,3,4,5,6,7,8,9})
    void getTableByDiferentsSize(long size){
        for (long i = 2; i <= size; i++){
            person.setId(i);
            list.add(person);
        }


        Mockito.when(personRepository.findAll(any(Sort.class)))
                .thenReturn(list);

        assertNotNull(personService.getTable());
        assertEquals(size, personService.getTable().size());
    }
}