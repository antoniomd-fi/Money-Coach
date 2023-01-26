package com.example.moneycoach.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
@Entity
@Data
//@RestResource(rel = "Entries", path = "entry")
public class Entry {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter private long id;

    @Column
    @NotNull(message = "The Entry must have a amount")
    @Getter @Setter private double amount;
    @Column
    @NotEmpty(message = "The Entry must have a concept")
    @Getter @Setter private String concept;
    @Column
    @Past(message = "The Entry date must be a past date")
    @Getter @Setter private LocalDateTime date;
    @Column()
    @NotNull(message = "The Entry myst have a user Id")
    @Getter @Setter long personId;


}
