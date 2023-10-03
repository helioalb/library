package me.helioalbano.library.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import me.helioalbano.library.domain.author.Author;
import me.helioalbano.library.domain.author.Name;
import me.helioalbano.library.repository.AuthorRepository;

public class AuthorServiceTest {
    private AuthorRepository authorRepository;
    private AuthorService authorService;

    @BeforeEach
    void setUp() {
        authorRepository = mock(AuthorRepository.class);
        authorService = new AuthorService(authorRepository);
    }

    @Test
    void givenInvalidParamsWhenCreatingAnAuthorThenThrowsAnException() {
        assertThrows(IllegalArgumentException.class,
                    () -> authorService.createAuthor(null));
    }

    @Test
    void givenValidParamsWhenCreatingAnAuthorThenReturns() {
        var authorFullName = "Machado de Assis";
        var author = new Author(10L, new Name(authorFullName));
        when(authorRepository.save(any(Author.class))).thenReturn(author);

        var authorId = authorService.createAuthor(authorFullName);

        assertEquals(10L, authorId);
    }

        @Test
    void givenAnInvalidAuthorIdWhenGettingAuthorByIdThenThrowsAnException() {
        var authorId = 1L;
        when(authorRepository.findById(authorId)).thenReturn(Optional.empty());

        var author = authorService.findAuthorById(authorId);

        assertTrue(author.isEmpty());
    }

    @Test
    void givenAValidAuthorIdWhenGettingAuthorByIdThenThrowsAnException() {
        var authorId = 1L;
        var fullName = "David Thomas";
        var author = new Author(authorId, new Name(fullName));
        when(authorRepository.findById(authorId)).thenReturn(Optional.of(author));

        var recoveredAuthor = authorService.findAuthorById(authorId).get();

        assertEquals(fullName, recoveredAuthor.fullName());
    }
}
