package com.wykessam.musicdatabase.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * DTO Representation of {@link Album}.
 */
@Data
@AllArgsConstructor
public class AlbumDTO {

    private Long id;

    private String title;

    private Long artist;

}
