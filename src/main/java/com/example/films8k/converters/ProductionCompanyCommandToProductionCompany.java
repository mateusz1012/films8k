package com.example.films8k.converters;

import com.example.films8k.commands.ProductionCompanyCommand;
import com.example.films8k.model.ProductionCompany;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class ProductionCompanyCommandToProductionCompany implements Converter<ProductionCompanyCommand, ProductionCompany> {

    @Synchronized
    @Nullable
    @Override
    public ProductionCompany convert(ProductionCompanyCommand source) {
        if (source == null) {
            return null;
        }

        final ProductionCompany productionCompany = new ProductionCompany();
        productionCompany.setName(source.getName());
        productionCompany.setNip(source.getNip());
        productionCompany.setAddress(source.getAddress());

        return productionCompany;
    }
}
