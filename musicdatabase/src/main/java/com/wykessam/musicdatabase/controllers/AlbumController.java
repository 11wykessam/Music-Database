package com.wykessam.musicdatabase.controllers;

import com.wykessam.musicdatabase.assemblers.AlbumAssembler;
import com.wykessam.musicdatabase.model.Album;
import com.wykessam.musicdatabase.services.AlbumService;
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

    @Autowired
    public AlbumController(AlbumService albumService, AlbumAssembler albumAssembler) {
        this.albumService = albumService;
        this.albumAssembler = albumAssembler;
    }

    /**
     * Mapping to get all artists.
     * @return {@link CollectionModel} object.
     */
    @GetMapping(value = "")
    public CollectionModel<EntityModel<Album>> all() {

        List<EntityModel<Album>> albums = albumService.all().stream()
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
    public EntityModel<Album> one(@PathVariable Long id) {

        return albumAssembler.toModel(albumService.getById(id));

    }

}
