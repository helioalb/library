package me.helioalbano.library.domain.book;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import me.helioalbano.library.domain.author.Author;
import me.helioalbano.library.domain.author.Name;

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

    @Test
    void givenAnAuthorWhenAddingItToBookThenBookAddTheAuthor() {
        var author = new Author(new Name("Machado de Assis"));
        var book = new Book(new Title("Dom Casmurro"));

        book.addAuthor(author);

        assertEquals(1, book.authors().size());
        assertTrue(book.authors().contains(author));
    }

}
