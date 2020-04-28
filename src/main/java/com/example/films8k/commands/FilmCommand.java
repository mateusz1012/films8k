package com.example.films8k.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FilmCommand {
    private Long id;
    private String title;
    private String premiere;
    private String filmGenre;
    private String budget;
    private Long productionCompanyId;
    private Long directorId;
}
