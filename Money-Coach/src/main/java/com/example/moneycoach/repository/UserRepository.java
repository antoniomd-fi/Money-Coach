package com.example.moneycoach.repository;

import com.example.moneycoach.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<Person, Long> {
}
