package com.wykessam.musicdatabase.repositories;

import com.wykessam.musicdatabase.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository responsible for storing artist data.
 */
public interface ArtistRepository extends JpaRepository<Artist, Long> {

}
