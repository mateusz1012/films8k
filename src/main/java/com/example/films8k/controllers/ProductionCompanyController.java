package com.example.films8k.controllers;

import com.example.films8k.commands.ProductionCompanyCommand;
import com.example.films8k.converters.ProductionCompanyCommandToProductionCompany;
import com.example.films8k.model.ProductionCompany;
import com.example.films8k.repositories.ProductionCompanyRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class ProductionCompanyController {

    private ProductionCompanyRepository productionCompanyRepository;
    private ProductionCompanyCommandToProductionCompany productionCompanyCommandToProductionCompany;

    public ProductionCompanyController(ProductionCompanyRepository productionCompanyRepository, ProductionCompanyCommandToProductionCompany productionCompanyCommandToProductionCompany) {
        this.productionCompanyRepository = productionCompanyRepository;
        this.productionCompanyCommandToProductionCompany = productionCompanyCommandToProductionCompany;
    }

    @RequestMapping(value = {"/productionsCompany", "/productionCompany/list"})
    public String getProductionsCompany(Model model) {
        model.addAttribute("productionsCompany", productionCompanyRepository.findAll());
        return "productionCompany/list";
    }

    @RequestMapping("/productionCompany/{id}/show")
    public String getProductionCompanyDetails(Model model, @PathVariable("id")Long id) {
        model.addAttribute("productionCompany", productionCompanyRepository.findById(id).get());
        return "productionCompany/show";
    }

    @RequestMapping("/productionCompany/{id}/delete")
    public String deleteProductionCompany(@PathVariable("id")Long id) {
        productionCompanyRepository.deleteById(id);
        return "redirect:/productionsCompany";
    }

    @GetMapping
    @RequestMapping("/productionCompany/new")
    public String newFilm(Model model) {
        model.addAttribute("productionCompany", new ProductionCompanyCommand());
        return "productionCompany/addedit";
    }

    @PostMapping("productionCompany")
    public String saveOrUpdate(@ModelAttribute ProductionCompanyCommand command) {

        Optional<ProductionCompany> productionCompanyOptional = productionCompanyRepository.getProductionCompanyByName(command.getName());

        if (!productionCompanyOptional.isPresent()) {
            ProductionCompany detachedProductionCompany = productionCompanyCommandToProductionCompany.convert(command);
            ProductionCompany savedProductionCompany = productionCompanyRepository.save(detachedProductionCompany);
            return "redirect:/productionCompany/" + savedProductionCompany.getId() + "/show";
        } else {
            //TODO: error message to template
            System.out.println("Sorry, there's such production company in db");
            return "redirect:/productionCompany/" + productionCompanyOptional.get().getId() + "/show";
        }
    }
}
