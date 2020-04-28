package com.example.films8k.controllers;

import com.example.films8k.commands.DirectorCommand;
import com.example.films8k.converters.DirectorCommandToDirector;
import com.example.films8k.model.Director;
import com.example.films8k.repositories.DirectorRepository;
import com.example.films8k.repositories.FilmRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class DirectorController {

    private DirectorRepository directorRepository;
    private FilmRepository filmRepository;
    private DirectorCommandToDirector directorCommandToDirector;

    public DirectorController(DirectorRepository directorRepository, FilmRepository filmRepository) {
        this.directorRepository = directorRepository;
        this.filmRepository = filmRepository;
    }

    @RequestMapping(value = {"/directors", "/director/list"})
    public String getDirectors(Model model) {
        model.addAttribute("directors", directorRepository.findAll());
        return "director/list";
    }

    @RequestMapping("/director/{id}/films")
    public String getDirectorFilms(Model model, @PathVariable("id") Long id) {
        Optional<Director> director = directorRepository.findById(id);

        if (director.isPresent()) {
            model.addAttribute("films", filmRepository.getAllByDirectorsIsContaining(director.get()));
            model.addAttribute("filter", "director: " + director.get().getFirstName() + " " + director.get().getLastName());
        } else {
            model.addAttribute("films", new ArrayList<>());
            model.addAttribute("filter", "director for this id doesn't exist");
        }

        return "film/list";
    }

    @RequestMapping("/director/{id}/show")
    public String getDirectorDetails(Model model, @PathVariable("id") Long id) {
        model.addAttribute("director", directorRepository.findById(id).get());
        return "director/show";
    }

    @RequestMapping("/director/{id}/delete")
    public String deleteDirector(@PathVariable("id") Long id) {
        directorRepository.deleteById(id);
        return "redirect:/directors";
    }

    @GetMapping
    @RequestMapping("/director/new")
    public String newFilm(Model model) {
        model.addAttribute("director", new DirectorCommand());
        return "director/addedit";
    }

    @PostMapping("director")
    public String saveOrUpdate(@ModelAttribute DirectorCommand command) {

        Optional<Director> directorOptional = directorRepository.getFirstByFirstNameAndLastName(command.getFirstName(), command.getLastName());

        if (!directorOptional.isPresent()) {
            Director detachedDirector = directorCommandToDirector.convert(command);
            Director saveDirector = directorRepository.save(detachedDirector);
            return "redirect:/director/" + saveDirector.getId() + "/show";
        } else {
            //TODO: error message to template
            System.out.println("Sorry, there's much director in db");
            return "redirect:/director/" + directorOptional.get().getId() + "/show";
        }
    }
}
