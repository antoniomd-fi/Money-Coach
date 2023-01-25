package com.example.moneycoach.entity;

import jakarta.persistence.*;
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
    @Getter @Setter private String concept;
    @Column
    @Getter @Setter private long amount;
    @Column
    @Getter @Setter private LocalDateTime date;
    @ManyToOne(optional = false)
    User user;
}
