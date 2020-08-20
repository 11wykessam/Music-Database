package com.wykessam.musicdatabase.controllers;

import com.wykessam.musicdatabase.assemblers.AlbumAssembler;
import com.wykessam.musicdatabase.assemblers.ArtistAssembler;
import com.wykessam.musicdatabase.model.Album;
import com.wykessam.musicdatabase.model.Artist;
import com.wykessam.musicdatabase.services.ArtistService;
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
 * Responsible for handling HTTP requests to do with Artists.
 */
@RequestMapping(value = "/artists")
@RestController
public class ArtistController {

    // the service for getting information from store.
    private final ArtistService artistService;

    // assembler responsible for producing artist entity models.
    private final ArtistAssembler artistAssembler;

    // assembler responsible for producing album entity models.
    private final AlbumAssembler albumAssembler;

    @Autowired
    public ArtistController(ArtistService artistService, ArtistAssembler artistAssembler, AlbumAssembler albumAssembler) {
        this.artistService = artistService;
        this.artistAssembler = artistAssembler;
        this.albumAssembler = albumAssembler;
    }

    /**
     * Mapping to get all artists.
     * @return {@link CollectionModel} object.
     */
    @GetMapping(value = "")
    public CollectionModel<EntityModel<Artist>> all() {

        List<EntityModel<Artist>> artists = artistService.all().stream()
                .map(artistAssembler::toModel).collect(Collectors.toList());

        return CollectionModel.of(
                artists,
                linkTo(methodOn(ArtistController.class).all()).withSelfRel()
        );

    }

    /**
     * Mapping to get a single artist.
     * @param id The id of the artist being requested.
     * @return {@link EntityModel} object.
     */
    @GetMapping(value = "/{id}")
    public EntityModel<Artist> one(@PathVariable Long id) {

        return artistAssembler.toModel(artistService.getById(id));

    }

    @GetMapping(value = "/{id}/albums")
    public CollectionModel<EntityModel<Album>> getAlbums(@PathVariable Long id) {

        // get list of albums.
        List<EntityModel<Album>> albums = artistService.getAlbumsByArtistId(id).stream()
                .map(albumAssembler::toModel).collect(Collectors.toList());

        return CollectionModel.of(
                albums,
                linkTo(methodOn(ArtistController.class).one(id)).withRel("artist")
        );
    }

}
