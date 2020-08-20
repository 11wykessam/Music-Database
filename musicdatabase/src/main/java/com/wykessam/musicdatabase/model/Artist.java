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
@Entity(name = "Artist")
@Table(name = "ARTIST")
@Data
@NoArgsConstructor
public class Artist {

    @Column(name = "ARTIST_ID")
    @Id @GeneratedValue private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @OneToMany(mappedBy = "ARTIST", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Album> albums = new ArrayList<>();

    public Artist(String name) {
        this.name = name;
        this.albums = new ArrayList<>();
    }

    public ArtistDTO toDTO() {

        List<Long> albumIds = albums.stream()
                .map(Album::getId).collect(Collectors.toList());

        return new ArtistDTO(id, name, albumIds);

    }


}
