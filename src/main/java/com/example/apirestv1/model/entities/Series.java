package com.example.apirestv1.model.entities;


import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@RequiredArgsConstructor
public class Series {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String genre;
    private int seasons;
    private int episodes;
}
