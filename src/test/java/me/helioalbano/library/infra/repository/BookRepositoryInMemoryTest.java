package me.helioalbano.library.infra.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import me.helioalbano.library.domain.Book;

public class BookRepositoryInMemoryTest {

    @Test
    void givenANewBookWhenSavingThenCreateANewIdAndSaveIt() {
        var repository = new BookRepositoryInMemory();
        var newBook = new Book(null, "Código Limpo");

        var savedBook = repository.save(newBook);

        assertEquals("Código Limpo", savedBook.getTitle());
        assertTrue(repository.findById(savedBook.getId()).isPresent());
    }

    @Test
    void givenAnExistentBookWhenSavingThenUpdateIt() {
        var repository = new BookRepositoryInMemory();
        var existentBook = new Book(10L, "Código Limpo");

        var savedBook = repository.save(existentBook);

        assertEquals("Código Limpo", savedBook.getTitle());
        assertTrue(repository.findById(savedBook.getId()).isPresent());
    }
}
