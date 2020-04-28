package com.example.films8k.tools;

import com.example.films8k.model.Director;
import com.example.films8k.model.Film;
import com.example.films8k.model.ProductionCompany;
import com.example.films8k.repositories.DirectorRepository;
import com.example.films8k.repositories.FilmRepository;
import com.example.films8k.repositories.ProductionCompanyRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DBInflater implements ApplicationListener<ContextRefreshedEvent> {

    public DBInflater(FilmRepository filmRepository, DirectorRepository directorRepository, ProductionCompanyRepository productionCompanyRepository) {
        this.filmRepository = filmRepository;
        this.directorRepository = directorRepository;
        this.productionCompanyRepository = productionCompanyRepository;
    }

    private FilmRepository filmRepository;
    private DirectorRepository directorRepository;
    private ProductionCompanyRepository productionCompanyRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) { initData(); }

    private void initData() {

        Director anthonyJoe = new Director("Anthony and Joe", "Russo", "49, 47");
        ProductionCompany marvelStudios = new ProductionCompany("Marvel Studios");
        Film avengersEndGame = new Film("Avengers End Game", "April 22, 2019", "Sci-Fi, Action",
                "$356 million", marvelStudios);
        anthonyJoe.getFilms().add(avengersEndGame);
        avengersEndGame.getDirectors().add(anthonyJoe);
        productionCompanyRepository.save(marvelStudios);
        directorRepository.save(anthonyJoe);
        filmRepository.save(avengersEndGame);

        Director michaelBay = new Director("Michael", "Bay", "54");
        ProductionCompany dreamWorks = new ProductionCompany("DreamWorks Pictures");
        Film transformers = new Film("Transformers", "June 12, 2007", "Sci-Fi, Action", "$150 million", dreamWorks);
        michaelBay.getFilms().add(transformers);
        transformers.getDirectors().add(michaelBay);
        productionCompanyRepository.save(dreamWorks);
        directorRepository.save(michaelBay);
        filmRepository.save(transformers);

        Director frankDarabont = new Director("Frank", "Darabont", "60");
        ProductionCompany castleRock = new ProductionCompany("Castle Rock Entertainment");
        Film greenMile = new Film("The Green Mile", "December 10, 1999", "Drama, Fantasy", "$60 million", castleRock);
        frankDarabont.getFilms().add(greenMile);
        greenMile.getDirectors().add(frankDarabont);
        productionCompanyRepository.save(castleRock);
        directorRepository.save(frankDarabont);
        filmRepository.save(greenMile);

        Director robertZemeckis = new Director("Robert", "Zemeckis", "62");
        ProductionCompany wendyFinerman = new ProductionCompany("Wendy Finerman Productions");
        Film forrestGump = new Film("Forrest Gump", "June 23, 1994", "Comedy-drama", "$55 million", wendyFinerman);
        robertZemeckis.getFilms().add(forrestGump);
        forrestGump.getDirectors().add(robertZemeckis);
        productionCompanyRepository.save(wendyFinerman);
        directorRepository.save(robertZemeckis);
        filmRepository.save(forrestGump);
    }
}
