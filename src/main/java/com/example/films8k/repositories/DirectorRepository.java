package com.example.films8k.repositories;

import com.example.films8k.model.Director;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DirectorRepository extends CrudRepository<Director, Long> {
    Optional<Director> getFirstByFirstNameAndLastName(String firstName, String lastName);
}
