package com.example.films8k.repositories;

import com.example.films8k.model.ProductionCompany;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProductionCompanyRepository extends CrudRepository<ProductionCompany, Long> {
    Optional<ProductionCompany> getProductionCompanyByName(String name);
}
