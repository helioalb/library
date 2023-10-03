package me.helioalbano.library.repository;

import java.util.Optional;

import me.helioalbano.library.domain.author.Author;

public interface AuthorRepository {
    Author save(Author author);
    Optional<Author> findById(Long authorId);
}
