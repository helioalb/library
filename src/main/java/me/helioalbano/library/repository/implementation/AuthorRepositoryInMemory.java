package me.helioalbano.library.repository.implementation;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import me.helioalbano.library.domain.author.Author;
import me.helioalbano.library.domain.author.Name;
import me.helioalbano.library.repository.AuthorRepository;

public class AuthorRepositoryInMemory implements AuthorRepository {
    private Map<Long, Author> authors = new LinkedHashMap<>();

    @Override
    public Author save(Author author) {
        Long id = author.id();

        if (id == null) {
            id = (long) (authors.size() + 1);
        }

        authors.put(id, new Author(id, new Name(author.fullName())));

        return authors.get(id);
    }

    @Override
    public Optional<Author> findById(Long authorId) {
        return Optional.ofNullable(authors.get(authorId));
    }

}
