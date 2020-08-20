package com.wykessam.musicdatabase.services;

import com.wykessam.musicdatabase.exceptions.ArtistNotFoundException;
import com.wykessam.musicdatabase.model.Album;
import com.wykessam.musicdatabase.model.Artist;
import com.wykessam.musicdatabase.repositories.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Handles services involving {@link com.wykessam.musicdatabase.model.Artist} objects.
 */
@Service
public class ArtistService {

    // the repository storing artist information.
    private final ArtistRepository artistRepository;

    @Autowired
    public ArtistService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    /**
     * Gets list of all {@link Artist} objects.
     * @return {@link List<Artist>} object.
     */
    public List<Artist> all() {

        return artistRepository.findAll();

    }

    /**
     * If it exists, get artist with given id.
     * @param id Id of {@link Artist} being requested.
     * @return {@link Artist} object.
     * @throws ArtistNotFoundException thrown if no artist with given id exists.
     */
    public Artist getById(Long id) throws ArtistNotFoundException {

        return artistRepository.findById(id)
                .orElseThrow(() -> new ArtistNotFoundException(id));

    }

    /**
     * If it exists, get all albums for given artist.
     * @param id Id of {@link Artist} being requested.
     * @return {@link List<Album>} object.
     * @throws ArtistNotFoundException thrown if no artist with given id exists.
     */
    public List<Album> getAlbumsByArtistId(Long id) throws ArtistNotFoundException {

        // extract the artist.
        Artist artist = getById(id);
        return artist.getAlbums();

    }

}
