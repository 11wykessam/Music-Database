package com.wykessam.musicdatabase.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity(name = "GENRE")
@Table(name = "GENRE")
@Data
@NoArgsConstructor
public class Genre {

    @Column(name = "GENRE_ID")
    @Id @GeneratedValue private Long id;

    @Column(name = "NAME")
    private String name;

    @ManyToMany(mappedBy = "genres", fetch = FetchType.EAGER)
    private List<Album> albums;

    public Genre(String name) {
        this.name = name;
        this.albums = new ArrayList<>();
    }

    public GenreDTO toDTO() {

        return new GenreDTO(id, name);

    }

}
