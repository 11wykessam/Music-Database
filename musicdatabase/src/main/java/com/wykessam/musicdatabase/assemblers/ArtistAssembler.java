package com.wykessam.musicdatabase.assemblers;

import com.wykessam.musicdatabase.controllers.ArtistController;
import com.wykessam.musicdatabase.model.Artist;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Class is responsible for converting an {@link Artist} object to an {@link EntityModel<Artist>} representation of it.
 */
@Component
public class ArtistAssembler implements RepresentationModelAssembler<Artist, EntityModel<Artist>> {

    @Override
    public EntityModel<Artist> toModel(Artist artist) {

        return EntityModel.of(
                artist,
                linkTo(methodOn(ArtistController.class).one(artist.getId())).withSelfRel(),
                linkTo(methodOn(ArtistController.class).all()).withRel("artists")
        );

    }

}
