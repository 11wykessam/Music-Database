package com.wykessam.musicdatabase.assemblers;

import com.wykessam.musicdatabase.controllers.AlbumController;
import com.wykessam.musicdatabase.model.Album;
import com.wykessam.musicdatabase.model.AlbumDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Class is responsible for converting an {@link Album} object to an {@link EntityModel<Album>} representation of it.
 */
@Component
public class AlbumAssembler implements RepresentationModelAssembler<Album, EntityModel<AlbumDTO>> {

    @Override
    public EntityModel<AlbumDTO> toModel(Album album) {
        return EntityModel.of(
                album.toDTO(),
                linkTo(methodOn(AlbumController.class).one(album.getId())).withSelfRel(),
                linkTo(methodOn(AlbumController.class).all()).withRel("albums"),
                linkTo(methodOn(AlbumController.class).getGenres(album.getId())).withRel("genres")
        );
    }
}
