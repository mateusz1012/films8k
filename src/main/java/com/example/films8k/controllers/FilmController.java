package com.example.films8k.controllers;

import com.example.films8k.commands.FilmCommand;
import com.example.films8k.converters.FilmCommandToFilm;
import com.example.films8k.model.Film;
import com.example.films8k.repositories.DirectorRepository;
import com.example.films8k.repositories.FilmRepository;
import com.example.films8k.repositories.ProductionCompanyRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class FilmController {

    private FilmRepository filmRepository;
    private FilmCommandToFilm filmCommandToFilm;
    private ProductionCompanyRepository productionCompanyRepository;
    private DirectorRepository directorRepository;

    public FilmController(FilmRepository filmRepository, FilmCommandToFilm filmCommandToFilm, ProductionCompanyRepository productionCompanyRepository, DirectorRepository directorRepository) {
        this.filmRepository = filmRepository;
        this.filmCommandToFilm = filmCommandToFilm;
        this.productionCompanyRepository = productionCompanyRepository;
        this.directorRepository = directorRepository;
    }

    @GetMapping
    @RequestMapping(value = {"/films", "film/list"})
    public String getFilms(Model model) {
        model.addAttribute("films", filmRepository.findAll());
        return "film/list";
    }

    @GetMapping
    @RequestMapping("/film/{id}/show")
    public String getFilmDetails(Model model, @PathVariable("id") Long id) {
        model.addAttribute("film", filmRepository.findById(id).get());
        return "film/show";
    }

    @GetMapping
    @RequestMapping("/film/{id}/delete")
    public String deleteFilm(@PathVariable("id") Long id) {
        filmRepository.deleteById(id);
        return "redirect:/films";
    }

    @GetMapping
    @RequestMapping("/film/new")
    public String newFilm(Model model) {
        model.addAttribute("film", new FilmCommand());
        model.addAttribute("productionsCompany", productionCompanyRepository.findAll());
        model.addAttribute("directors", directorRepository.findAll());
        return "film/addedit";
    }

    @PostMapping("film")
    public String saveOrUpdate(@ModelAttribute FilmCommand command) {
        Film detachedFilm = filmCommandToFilm.convert(command);
        Film savedFilm = filmRepository.save(detachedFilm);

        return "redirect:/film/" + savedFilm.getId() + "/show";
    }
}
