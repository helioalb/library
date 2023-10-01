package me.helioalbano.library.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import me.helioalbano.library.domain.book.Book;
import me.helioalbano.library.domain.book.Title;
import me.helioalbano.library.repository.BookRepository;
import me.helioalbano.library.service.exception.BookNotFoundException;

public class BookServiceTest {
    private BookRepository bookRepository;
    private BookService bookService;

    @BeforeEach
    void setUp() {
        bookRepository = mock(BookRepository.class);
        bookService = new BookService(bookRepository);
    }

    @Test
    void givenAValidBookWhenCreatingThenReturnsItsId() {
        var validBook = new Book(1L, new Title("Entendendo Algoritmos"));
        when(bookRepository.save(any(Book.class))).thenReturn(validBook);

        var bookId = bookService.createBook("Entendendo Algoritmos");

        assertEquals(1, bookId);
    }

    @Test
    void givenAnInvalidBookWhenCreatingThenReturnsItsId() {
        when(bookRepository.save(any(Book.class)))
            .thenThrow(new IllegalArgumentException());

        assertThrows(IllegalArgumentException.class,
            () -> bookService.createBook(null));
    }

    @Test
    void givenAnInvalidBookIdWhenGettingBookByIdThenThrowsAnException() {
        var bookId = 1L;
        when(bookRepository.findById(bookId)).thenReturn(Optional.empty());

        assertThrows(BookNotFoundException.class,
            () -> bookService.getBookById(bookId));
    }

    @Test
    void givenAValidBookIdWhenGettingBookByIdThenThrowsAnException() {
        var bookId = 1L;
        var title = "Implementando Domain-Driven Design";
        var book = new Book(bookId, new Title(title));
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));

        var recoveredBook = bookService.getBookById(bookId);

        assertEquals(title, recoveredBook.getTitle().toString());
    }
}
