package com.example.films8k.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductionCompanyCommand {
    private Long id;
    private String name;
    private String nip;
    private String address;
}
