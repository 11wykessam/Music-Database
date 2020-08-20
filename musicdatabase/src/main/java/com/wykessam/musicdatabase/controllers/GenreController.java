package com.wykessam.musicdatabase.controllers;

import com.wykessam.musicdatabase.assemblers.GenreAssembler;
import com.wykessam.musicdatabase.model.Genre;
import com.wykessam.musicdatabase.model.GenreDTO;
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
 * Responsible for handling HTTP requests involving genre data.
 */
@RequestMapping("/genres")
@RestController
public class GenreController {

    // the service for handling genre objects.
    private final GenreService genreService;

    // assembler to produce entity models.
    private final GenreAssembler genreAssembler;

    @Autowired
    public GenreController(GenreService genreService, GenreAssembler genreAssembler) {
        this.genreService = genreService;
        this.genreAssembler = genreAssembler;
    }

    /**
     * Mapping to return all genres in store.
     * @return {@link CollectionModel} object.
     */
    @GetMapping("")
    public CollectionModel<EntityModel<GenreDTO>> all() {

        List<EntityModel<GenreDTO>> genres = genreService.all().stream()
                .map(genreAssembler::toModel).collect(Collectors.toList());

        return CollectionModel.of(
                genres,
                linkTo(methodOn(GenreController.class).all()).withSelfRel()
        );

    }

    @GetMapping("/{id}")
    public EntityModel<GenreDTO> one(@PathVariable Long id) {

        return genreAssembler.toModel(genreService.getById(id));

    }

}
