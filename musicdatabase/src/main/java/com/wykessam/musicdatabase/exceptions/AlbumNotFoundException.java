package com.wykessam.musicdatabase.exceptions;

/**
 * Exception thrown when album is not found in database.
 */
public class AlbumNotFoundException extends RuntimeException{

    public AlbumNotFoundException(Long id) {
        super("Could not find album " + id);
    }

}
