package me.helioalbano.library.domain.author;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class AuthorTest {

    @Test
    void givenAnAuthorWithNullNameWhenCreatingThenThrowsAnException() {
        var ex = assertThrows(IllegalArgumentException.class,
            () -> new Author(null));

        assertEquals("Author name cannot be null", ex.getMessage());
    }

    @Test
    void givenAValidAuthorWhenCreatingThenCreateIt() {
        var author = new Author(new Name("Machado de Assis"));

        assertEquals("Machado de Assis", author.fullName());
    }
}
