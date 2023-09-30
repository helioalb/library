package me.helioalbano.library.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

import me.helioalbano.library.domain.book.Book;
import me.helioalbano.library.domain.book.Title;
import me.helioalbano.library.repository.BookRepository;

public class BookServiceTest {

    @Test
    void givenAValidBookWhenCreatingThenReturnsItsId() {
        var bookRepository = mock(BookRepository.class);
        var bookService = new BookService(bookRepository);
        var validBook = new Book(1L, new Title("Entendendo Algoritmos"));
        when(bookRepository.save(any(Book.class))).thenReturn(validBook);

        var bookId = bookService.createBook("Entendendo Algoritmos");

        assertEquals(1, bookId);
    }

    @Test
    void givenAnInvalidBookWhenCreatingThenReturnsItsId() {
        var bookRepository = mock(BookRepository.class);
        var bookService = new BookService(bookRepository);
        when(bookRepository.save(any(Book.class)))
            .thenThrow(new IllegalArgumentException());

        assertThrows(IllegalArgumentException.class,
            () -> bookService.createBook(null));
    }
}
