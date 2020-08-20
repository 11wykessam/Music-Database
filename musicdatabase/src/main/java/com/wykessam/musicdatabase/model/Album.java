package com.wykessam.musicdatabase.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * JPA Representation of an album.
 */
@Entity
@Data
@NoArgsConstructor
public class Album {

    @Column(name = "ALBUM_ID")
    @Id @GeneratedValue private Long id;

    @Column(name = "TITLE")
    private String title;

    public Album(String title) {
        this.title = title;
    }

    public AlbumDTO toDTO() {

        return new AlbumDTO(id, title);
        
    }
}
