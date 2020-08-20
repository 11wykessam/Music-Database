package com.wykessam.musicdatabase.assemblers;

import com.wykessam.musicdatabase.model.Album;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

/**
 * Class is responsible for converting an {@link Album} object to an {@link EntityModel<Album>} representation of it.
 */
@Component
public class AlbumAssembler implements RepresentationModelAssembler<Album, EntityModel<Album>> {

    @Override
    public EntityModel<Album> toModel(Album album) {
        return EntityModel.of(
                album
        );
    }
}
