package com.example.moneycoach.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.rest.core.annotation.RestResource;

@Entity
@Data
@RestResource(rel = "Users", path = "users")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter private long id;
    @Column
    @Getter @Setter private String name;
    @Column
    @Getter @Setter private String lastname;
    @Column
    @Getter @Setter private String username;
}
