package me.helioalbano.library.domain.author;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class NameTest {

    @Test
    void givenANullNameWhenCreatingThenThrowsAnException() {
        var ex = assertThrows(IllegalArgumentException.class,
            () -> new Name(null));

        assertEquals("Author name cannot be null", ex.getMessage());
    }

    @Test
    void givenAEmptyNameWhenCreatingThenThrowsAnException() {
        var ex = assertThrows(IllegalArgumentException.class,
            () -> new Name(""));

        assertEquals("Author name cannot be blank", ex.getMessage());
    }

    @Test
    void givenAnBlankNameWhenCreatingThenThrowsAnException() {
        var ex = assertThrows(IllegalArgumentException.class,
            () -> new Name("          "));

        assertEquals("Author name cannot be blank", ex.getMessage());
    }

    @Test
    void givenANameGreaterThan100WhenCreatingThenThrowsAnException() {
        var name = "a".repeat(101);

        var ex = assertThrows(IllegalArgumentException.class,
            () -> new Name(name));

        assertEquals("Author name cannot be greater than 100", ex.getMessage());
    }

    @Test
    void givenANameWithSpacesInFrontAndAtTheAndWhenCreatingThenRemoveThoseSpaces() {
        var name = new Name("   Fulano  ");

        assertEquals("Fulano", name.full());
    }

    @Test
    void givenAValidNameWhenCreatingThenCreate() {
        var name = new Name("Machado de Assis");

        assertEquals("Machado de Assis", name.full());
    }
}
