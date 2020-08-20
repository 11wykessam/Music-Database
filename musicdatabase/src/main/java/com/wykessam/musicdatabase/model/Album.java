package com.wykessam.musicdatabase.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * JPA Representation of an album.
 */
@Entity(name = "ALBUM")
@Table(name = "ALBUM")
@Data
@NoArgsConstructor
public class Album {

    @Column(name = "ALBUM_ID")
    @Id @GeneratedValue private Long id;

    @Column(name = "TITLE")
    private String title;

    @ManyToOne
    @JoinColumn(name = "ARTIST_ID", nullable = false)
    private Artist artist;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "ALBUM_GENRE",
            joinColumns = @JoinColumn(name = "ALBUM_ID", referencedColumnName = "ALBUM_ID"),
            inverseJoinColumns = @JoinColumn(name = "GENRE_ID", referencedColumnName = "GENRE_ID")
    )
    private List<Genre> genres;

    public Album(String title, Artist artist) {
        this.title = title;
        this.artist = artist;
        this.genres = new ArrayList<>();
    }

    public AlbumDTO toDTO() {

        return new AlbumDTO(id, title, artist.getId());
        
    }
}
