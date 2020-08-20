package com.wykessam.musicdatabase.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * JPA Representation of an artist.
 */
@Entity
@Data
@NoArgsConstructor
public class Artist {

    @Column(name = "ARTIST_ID")
    @Id @GeneratedValue private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    public Artist(String name) {
        this.name = name;
    }

    public ArtistDTO toDTO() {

        return new ArtistDTO(id, name, null);

    }


}
