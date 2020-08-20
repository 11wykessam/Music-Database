package com.wykessam.musicdatabase.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * DTO Representation of {@link Artist}.
 */
@Data
@AllArgsConstructor
public class ArtistDTO {

    private Long id;

    private String name;

    private List<Long> albums;

}
