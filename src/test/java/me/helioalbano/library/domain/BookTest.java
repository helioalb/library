package me.helioalbano.library.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class BookTest {

    @Test
    void givenABookWithNullTitleWhenInstantiatingItThenThrowsAnException() {
        var exception = assertThrows(IllegalArgumentException.class,
                            () -> new Book(null, null));

        assertEquals("Book title cannot be null", exception.getMessage());
    }

    @Test
    void giventABookWithEmptyTitleWhenInstantiatingItThenThrowsAnException() {
        var exception = assertThrows(IllegalArgumentException.class,
                            () -> new Book(null, ""));

        assertEquals("Book title cannot be empty", exception.getMessage());
    }

    @Test
    void giventABookWithTitleGreaterThant100WhenInstantiatingItThenThrowsAnException() {
        var title = "a".repeat(101);
        var exception =
            assertThrows(IllegalArgumentException.class,
                () -> new Book(null, title));

        assertEquals("Book title cannot be greater than 100", exception.getMessage());
    }

    @Test
    void givenAValidBookWhenInstantiatingItThenCreateANewInstance() {
        var title = "The clean code";
        var book = new Book(null, title);
        assertEquals("The clean code", book.getTitle());
    }
}
