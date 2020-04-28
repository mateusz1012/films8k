package com.example.films8k.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@Entity
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String premiere;
    private String filmGenre;
    private String budget;

    @ManyToOne
    private ProductionCompany productionCompany;

    @ManyToMany
    private Set<Director> directors = new HashSet<>();

    public Film(String title, String premiere, String filmGenre, String budget, ProductionCompany productionCompany) {
        this.title = title;
        this.premiere = premiere;
        this.filmGenre = filmGenre;
        this.budget = budget;
        this.productionCompany = productionCompany;
    }
}

