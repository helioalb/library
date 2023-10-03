package me.helioalbano.library.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

import me.helioalbano.library.domain.author.Author;
import me.helioalbano.library.domain.author.Name;
import me.helioalbano.library.repository.AuthorRepository;

public class AuthorServiceTest {

    @Test
    void givenInvalidParamsWhenCreatingAnAuthorThenThrowsAnException() {
        var authorRepository = mock(AuthorRepository.class);
        var authorService = new AuthorService(authorRepository);

        assertThrows(IllegalArgumentException.class,
                    () -> authorService.createAuthor(null));
    }

    @Test
    void givenValidParamsWhenCreatingAnAuthorThenReturns() {
        var authorFullName = "Machado de Assis";
        var authorRepository = mock(AuthorRepository.class);
        var authorService = new AuthorService(authorRepository);
        var author = new Author(10L, new Name(authorFullName));
        when(authorRepository.save(any(Author.class))).thenReturn(author);

        var authorId = authorService.createAuthor(authorFullName);

        assertEquals(10L, authorId);
    }
}
