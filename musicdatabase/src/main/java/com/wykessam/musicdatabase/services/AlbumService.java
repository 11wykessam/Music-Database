package com.wykessam.musicdatabase.services;

import com.wykessam.musicdatabase.exceptions.AlbumNotFoundException;
import com.wykessam.musicdatabase.model.Album;
import com.wykessam.musicdatabase.repositories.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Handles services involving {@link com.wykessam.musicdatabase.model.Album} objects.
 */
@Service
public class AlbumService {

    // the repository storing album information.
    private final AlbumRepository albumRepository;

    @Autowired
    public AlbumService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    /**
     * Get list of all {@link Album} objects.
     * @return {@link List<Album>} object.
     */
    public List<Album> all() {

        return albumRepository.findAll();

    }

    /**
     * If it exists, get an album with given id.
     * @param id Id of {@link Album} being requested.
     * @return {@link Album} object.
     * @throws AlbumNotFoundException thrown if no album with id found.
     */
    public Album getById(Long id) throws AlbumNotFoundException {

        return albumRepository.findById(id)
                .orElseThrow(() -> new AlbumNotFoundException(id));

    }

}
