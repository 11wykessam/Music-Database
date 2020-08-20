package com.wykessam.musicdatabase.exceptions;

/**
 * Exception thrown when a {@link com.wykessam.musicdatabase.model.Genre} object doesn't exist in system.
 */
public class GenreNotFoundException extends RuntimeException{

    public GenreNotFoundException(Long id) {
        super("Could not find genre " + id);
    }

}
