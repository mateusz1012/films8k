package com.example.films8k.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FilmsController {

    @RequestMapping(value = {"/"})
    public String getDirectors() { return "index"; }
}
