package com.example.moneycoach.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.data.rest.core.annotation.RestResource;

import java.time.LocalDateTime;
@Entity
@Data
@RestResource(rel = "Exits", path = "exit")
public class Exit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter private long id;
    @Column
    @NotEmpty(message = "The Exit must have a concept")
    @Getter @Setter private String concept;
    @Column
    @NotNull(message = "The Exit must have a amount")
    @Getter @Setter private double amount;
    @Column
    @Past(message = "The Exit date must be a past date")
    @Getter @Setter private LocalDateTime date;

    @Column()
    @NotNull(message = "The Entry myst have a user Id")
    @Getter @Setter long personId;

}
