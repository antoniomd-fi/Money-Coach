package com.example.moneycoach.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.data.rest.core.annotation.RestResource;

@Entity
@Data
@RestResource(rel = "Users", path = "user")
public class User {
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
