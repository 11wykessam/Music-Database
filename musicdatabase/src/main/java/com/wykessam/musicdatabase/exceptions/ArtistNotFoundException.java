package com.wykessam.musicdatabase.exceptions;

/**
 * Exception thrown when artist is not found in database.
 */
public class ArtistNotFoundException extends RuntimeException{

    public ArtistNotFoundException(Long id) {
        super("Could not find artist " + id);
    }

}
