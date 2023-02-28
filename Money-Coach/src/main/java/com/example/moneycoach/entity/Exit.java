package com.example.moneycoach.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.data.rest.core.annotation.RestResource;

import java.time.LocalDateTime;
@Entity
@Table(name = "exits"/*, schema = "heroku_7f60c85dc94d362"*/)
@Data
//@RestResource(rel = "Exits", path = "exit")
public class Exit {
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter private long id;

    //@Column
    @NotNull(message = "The Exit must have a amount")
    @Getter @Setter private Double amount;
    //@Column
    @NotEmpty(message = "The Exit must have a concept")
    @Getter @Setter private String concept;
    //@Column
    //@Past(message = "The Exit date must be a past date")
    @NotEmpty(message = "The Exit must have a date")
    @Getter @Setter private String date;
    //@Column()
    @NotNull(message = "The Exit myst have a user Id")
    @Getter @Setter long person_id;

}
