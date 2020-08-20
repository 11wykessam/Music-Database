package com.wykessam.musicdatabase.repositories;

import com.wykessam.musicdatabase.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository responsible for storing genre data.
 */
public interface GenreRepository extends JpaRepository<Genre, Long> {
}
