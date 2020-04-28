package com.example.films8k.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class DirectorCommand {
    private Long id;
    private String firstName;
    private String lastName;
    private String age;
}
