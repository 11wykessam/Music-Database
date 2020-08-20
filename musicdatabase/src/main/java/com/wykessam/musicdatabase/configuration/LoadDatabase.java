package com.wykessam.musicdatabase.configuration;

import com.wykessam.musicdatabase.model.Album;
import com.wykessam.musicdatabase.model.Artist;
import com.wykessam.musicdatabase.model.Genre;
import com.wykessam.musicdatabase.repositories.AlbumRepository;
import com.wykessam.musicdatabase.repositories.ArtistRepository;
import com.wykessam.musicdatabase.repositories.GenreRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * Class responsible for loading in data for testing purposes.
 */
@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(ArtistRepository artistRepository, AlbumRepository albumRepository, GenreRepository genreRepository) {

        return args -> {

            Artist theBeatles = new Artist("The Beatles");
            Artist theCure = new Artist("The Cure");

            Album abbeyRoad = new Album("Abbey Road", theBeatles);
            Album disintegration = new Album("Disintegration", theCure);

            Genre rock = new Genre("rock");
            abbeyRoad.getGenres().add(rock);

            log.info("Preloading " + genreRepository.save(rock));

            log.info("Preloading " + artistRepository.save(theBeatles));
            log.info("Preloading " + artistRepository.save(theCure));

            log.info("Preloading " + albumRepository.save(abbeyRoad));
            log.info("Preloading " + albumRepository.save(disintegration));

        };
    }
}
