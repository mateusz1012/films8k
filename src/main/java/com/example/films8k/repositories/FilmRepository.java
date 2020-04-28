package com.example.films8k.repositories;

import com.example.films8k.model.Director;
import com.example.films8k.model.Film;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FilmRepository extends CrudRepository<Film, Long> {

    List<Film> getAllByDirectorsIsContaining(Director director);
}
