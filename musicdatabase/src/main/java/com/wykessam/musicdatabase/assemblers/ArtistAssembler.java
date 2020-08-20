package com.wykessam.musicdatabase.assemblers;

import com.wykessam.musicdatabase.controllers.ArtistController;
import com.wykessam.musicdatabase.model.Artist;
import com.wykessam.musicdatabase.model.ArtistDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Class is responsible for converting an {@link Artist} object to an {@link EntityModel<Artist>} representation of it.
 */
@Component
public class ArtistAssembler implements RepresentationModelAssembler<Artist, EntityModel<ArtistDTO>> {

    @Override
    public EntityModel<ArtistDTO> toModel(Artist artist) {

        return EntityModel.of(
                artist.toDTO(),
                linkTo(methodOn(ArtistController.class).one(artist.getId())).withSelfRel(),
                linkTo(methodOn(ArtistController.class).all()).withRel("artists"),
                linkTo(methodOn(ArtistController.class).getAlbums(artist.getId())).withRel("albums")
        );

    }

}
