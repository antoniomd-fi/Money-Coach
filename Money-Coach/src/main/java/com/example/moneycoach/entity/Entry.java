package com.example.moneycoach.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.data.rest.core.annotation.RestResource;

import java.time.LocalDateTime;
@Entity
@Data
@RestResource(rel = "Entries", path = "entry")
public class Entry {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter private long id;
    @Column
    @NotEmpty(message = "The Entry must have a concept") @Getter @Setter private String concept;
    @Column
    @NotEmpty(message = "The Entry must have a amount") @Getter @Setter private long amount;
    @Column
    @Past(message = "The Entry date must be a past date") @Getter @Setter private LocalDateTime date;
    @ManyToOne(optional = false)
    Person person;
}
