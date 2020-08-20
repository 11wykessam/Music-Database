package com.wykessam.musicdatabase.controllers;

import com.wykessam.musicdatabase.assemblers.AlbumAssembler;
import com.wykessam.musicdatabase.assemblers.GenreAssembler;
import com.wykessam.musicdatabase.model.Album;
import com.wykessam.musicdatabase.model.AlbumDTO;
import com.wykessam.musicdatabase.model.GenreDTO;
import com.wykessam.musicdatabase.services.AlbumService;
import com.wykessam.musicdatabase.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Responsible for handling HTTP requests to do with Albums.
 */
@RequestMapping(value = "/albums")
@RestController
public class AlbumController {

    // the service for getting information from the store.
    private final AlbumService albumService;

    // the assembler responsible for producing entity models.
    private final AlbumAssembler albumAssembler;

    // the assembler responsible for producing entity modles of genres.
    private final GenreAssembler genreAssembler;

    @Autowired
    public AlbumController(AlbumService albumService, AlbumAssembler albumAssembler, GenreAssembler genreAssembler) {
        this.albumService = albumService;
        this.albumAssembler = albumAssembler;
        this.genreAssembler = genreAssembler;
    }

    /**
     * Mapping to get all artists.
     * @return {@link CollectionModel} object.
     */
    @GetMapping(value = "")
    public CollectionModel<EntityModel<AlbumDTO>> all() {

        List<EntityModel<AlbumDTO>> albums = albumService.all().stream()
                .map(albumAssembler::toModel).collect(Collectors.toList());

        return CollectionModel.of(
                albums,
                linkTo(methodOn(AlbumController.class).all()).withSelfRel()
        );

    }

    /**
     * Mapping to get a single album.
     * @param id The id of the album being requested.
     * @return {@link EntityModel} object.
     */
    @GetMapping(value = "/{id}")
    public EntityModel<AlbumDTO> one(@PathVariable Long id) {

        return albumAssembler.toModel(albumService.getById(id));

    }

    /**
     * Mapping to get genres associated with album.
     * @param id Id of the album.
     * @return {@link CollectionModel} object.
     */
    @GetMapping(value = "/{id}/genres")
    public CollectionModel<EntityModel<GenreDTO>> getGenres(@PathVariable Long id) {

        // get list of entity models.
        List<EntityModel<GenreDTO>> genres = albumService.getGenresByAlbumId(id).stream()
                .map(genreAssembler::toModel).collect(Collectors.toList());

        return CollectionModel.of(
                genres,
                linkTo(methodOn(GenreController.class).all()).withRel("genres")
        );

    }

}
