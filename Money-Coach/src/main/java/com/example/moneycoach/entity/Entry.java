package com.example.moneycoach.entity;

import lombok.*;

public class Entry {
    @Getter @Setter private long id;
    @Getter @Setter private String concept;
    @Getter @Setter private long amount;
}
