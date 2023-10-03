package me.helioalbano.library.repository.implementation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import me.helioalbano.library.domain.author.Author;
import me.helioalbano.library.domain.author.Name;

public class AuthorRepositoryInMemoryTest {

    @Test
    void givenANewAuthorWhenSavingThenCreateANewIdAndSaveIt() {
        var repository = new AuthorRepositoryInMemory();
        var newAuthor = new Author(new Name("Machado de Assis"));

        var savedAuthor = repository.save(newAuthor);

        assertEquals("Machado de Assis", savedAuthor.fullName());
        assertTrue(repository.findById(savedAuthor.getId()).isPresent());
    }

    @Test
    void givenAnExistentBookWhenSavingThenUpdateIt() {
        var repository = new AuthorRepositoryInMemory();
        var existentAuthor = new Author(10L, new Name("Machado de Assis"));

        var savedAuthor = repository.save(existentAuthor);

        assertEquals("Machado de Assis", savedAuthor.fullName());
        assertTrue(repository.findById(savedAuthor.getId()).isPresent());
    }
}
