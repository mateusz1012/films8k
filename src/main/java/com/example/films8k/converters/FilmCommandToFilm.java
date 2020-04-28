package com.example.films8k.converters;

import com.example.films8k.commands.FilmCommand;
import com.example.films8k.model.Film;
import com.example.films8k.model.ProductionCompany;
import com.example.films8k.model.Director;
import com.example.films8k.repositories.DirectorRepository;
import com.example.films8k.repositories.ProductionCompanyRepository;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class FilmCommandToFilm implements Converter<FilmCommand, Film> {

    private DirectorRepository directorRepository;
    private ProductionCompanyRepository productionCompanyRepository;

    public FilmCommandToFilm(DirectorRepository directorRepository, ProductionCompanyRepository productionCompanyRepository) {
        this.directorRepository = directorRepository;
        this.productionCompanyRepository = productionCompanyRepository;
    }

    @Synchronized
    @Nullable
    @Override
    public Film convert(FilmCommand source) {
        if (source == null) {
            return null;
        }

        final Film film = new Film();
        film.setId(source.getId());
        film.setTitle(source.getTitle());
        film.setPremiere(source.getPremiere());
        film.setFilmGenre(source.getFilmGenre());
        film.setBudget(source.getBudget());

        Optional<ProductionCompany> productionCompany = productionCompanyRepository.findById(source.getProductionCompanyId());

        if (productionCompany.isPresent()) {
            film.setProductionCompany(productionCompany.get());
        } else {
            film.setProductionCompany(productionCompanyRepository.getProductionCompanyByName("Unknown").get());
        }

        Optional<Director> director = directorRepository.findById(source.getDirectorId());

        if (director.isPresent()) {
            film.getDirectors().add(director.get());
        }

        return film;
    }
}
