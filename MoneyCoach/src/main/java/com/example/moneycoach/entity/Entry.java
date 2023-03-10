package com.example.moneycoach.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;


import java.time.LocalDateTime;
@Entity
@Table(name = "entry"/*,schema = "heroku_7f60c85dc94d362"*/)
@Data

//@RestResource(rel = "Entries", path = "entry")
public class Entry {
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter private long id;

    //@Column
    @NotNull(message = "The Entry must have a amount")
    @Getter @Setter private Double amount;
    //@Column
    @NotEmpty(message = "The Entry must have a concept")
    @Getter @Setter private String concept;
    //@Column
    //@Past(message = "The Entry date must be a past date")
    @NotEmpty(message = "The Entry must have a date")
    @Getter @Setter private String date;
    //@Column()
    @NotNull(message = "The Entry myst have a user Id")
    @Getter @Setter long person_id;

}
