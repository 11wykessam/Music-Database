package com.wykessam.musicdatabase.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * JPA Representation of an artist.
 */
@Entity(name = "ARTIST")
@Table(name = "ARTIST")
@Data
@NoArgsConstructor
public class Artist {

    @Column(name = "ARTIST_ID")
    @Id @GeneratedValue private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @OneToMany(mappedBy = "artist")
    private List<Album> albums;

    public Artist(String name) {
        this.name = name;
        this.albums = new ArrayList<>();
    }

    public ArtistDTO toDTO() {

        return new ArtistDTO(id, name);

    }


}
