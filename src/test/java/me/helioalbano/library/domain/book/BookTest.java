package me.helioalbano.library.domain.book;

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
    void givenAValidBookWhenInstantiatingItThenCreateANewInstance() {
        var book = new Book(null, new Title("The clean code"));
        assertEquals("The clean code", book.title());
    }
}
