package me.helioalbano.library.domain.book;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class TitleTest {
    @Test
    void givenANullTitleWhenInstantiatingItThenThrowsAnException() {
        var exception = assertThrows(IllegalArgumentException.class,
                            () -> new Title(null));

        assertEquals("Book title cannot be null", exception.getMessage());
    }

    @Test
    void giventAEmptyTitleWhenInstantiatingItThenThrowsAnException() {
        var exception = assertThrows(IllegalArgumentException.class,
                            () -> new Title(""));

        assertEquals("Book title cannot be empty", exception.getMessage());
    }

    @Test
    void giventATitleGreaterThant100WhenInstantiatingItThenThrowsAnException() {
        var title = "a".repeat(101);
        var exception =
            assertThrows(IllegalArgumentException.class,
                () -> new Title(title));

        assertEquals("Book title cannot be greater than 100", exception.getMessage());
    }

    @Test
    void givenATitleWithSpacesAtStartAndEndWhenCreatingItThenRemoveThoseSpaces() {
        var title = new Title("    The book  ");

        assertEquals("The book", title.toString());
    }
}
