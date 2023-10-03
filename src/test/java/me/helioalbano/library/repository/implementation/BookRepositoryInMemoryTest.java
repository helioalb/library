package me.helioalbano.library.repository.implementation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import me.helioalbano.library.domain.book.Book;
import me.helioalbano.library.domain.book.Title;

public class BookRepositoryInMemoryTest {

    @Test
    void givenANewBookWhenSavingThenCreateANewIdAndSaveIt() {
        var repository = new BookRepositoryInMemory();
        var newBook = new Book(null, new Title("Código Limpo"));

        var savedBook = repository.save(newBook);

        assertEquals("Código Limpo", savedBook.title());
        assertTrue(repository.findById(savedBook.id()).isPresent());
    }

    @Test
    void givenAnExistentBookWhenSavingThenUpdateIt() {
        var repository = new BookRepositoryInMemory();
        var existentBook = new Book(10L, new Title("Código Limpo"));

        var savedBook = repository.save(existentBook);

        assertEquals("Código Limpo", savedBook.title());
        assertTrue(repository.findById(savedBook.id()).isPresent());
    }
}
