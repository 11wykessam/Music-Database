package com.wykessam.musicdatabase.services;

import com.wykessam.musicdatabase.exceptions.GenreNotFoundException;
import com.wykessam.musicdatabase.model.Genre;
import com.wykessam.musicdatabase.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Handles services involving {@link com.wykessam.musicdatabase.model.Artist} objects.
 */
@Service
public class GenreService {

    // the repository responsible for storing genre data.
    private final GenreRepository genreRepository;

    @Autowired
    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    /**
     * Gets a list of all the {@link Genre} objects.
     * @return {@link List<Genre>} object.
     */
    public List<Genre> all() {

        return genreRepository.findAll();

    }

    /**
     * If it exists, get {@link Genre} object with specific id.
     * @param id Id of genre.
     * @return {@link Genre} object.
     * @throws GenreNotFoundException thrown if genre with id cannot be found.
     */
    public Genre getById(Long id) throws GenreNotFoundException {

        return genreRepository.findById(id)
                .orElseThrow(() -> new GenreNotFoundException(id));

    }

}
