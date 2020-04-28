package com.example.films8k.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@EqualsAndHashCode(of = {"id"})
@Entity
public class ProductionCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String nip;
    private String address;

    public ProductionCompany() {
    }
    public ProductionCompany(String name) { this.name = name; }
    public ProductionCompany(String name, String nip, String address) {
        this.name = name;
        this.nip = nip;
        this.address = address;
    }
}
