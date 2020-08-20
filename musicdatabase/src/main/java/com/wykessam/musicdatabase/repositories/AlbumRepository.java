package com.wykessam.musicdatabase.repositories;

import com.wykessam.musicdatabase.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository responsible for storing album data.
 */
public interface AlbumRepository extends JpaRepository<Album, Long> {

}
