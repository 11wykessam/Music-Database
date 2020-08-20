package com.wykessam.musicdatabase.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * DTO for genres.
 */
@Data
@AllArgsConstructor
public class GenreDTO {

    private Long id;

    private String name;

}
