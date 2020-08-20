package com.wykessam.musicdatabase.assemblers;

import com.wykessam.musicdatabase.controllers.GenreController;
import com.wykessam.musicdatabase.model.Genre;
import com.wykessam.musicdatabase.model.GenreDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Class is responsible for converting {@link Genre} objects to {@link EntityModel<GenreDTO>} objects.
 */
@Component
public class GenreAssembler implements RepresentationModelAssembler<Genre, EntityModel<GenreDTO>> {
    @Override
    public EntityModel<GenreDTO> toModel(Genre genre) {
        return EntityModel.of(
                genre.toDTO(),
                linkTo(methodOn(GenreController.class).one(genre.getId())).withSelfRel(),
                linkTo(methodOn(GenreController.class).all()).withRel("genres")
        );
    }
}
