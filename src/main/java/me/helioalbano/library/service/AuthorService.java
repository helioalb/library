package me.helioalbano.library.service;

import me.helioalbano.library.domain.author.Author;
import me.helioalbano.library.domain.author.Name;
import me.helioalbano.library.repository.AuthorRepository;

public class AuthorService {
    private AuthorRepository authorRepository;

    public AuthorService(final AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Long createAuthor(String fullName) {
        var newAuthor = new Author(new Name(fullName));
        var savedAuthor = authorRepository.save(newAuthor);

        return savedAuthor.getId();
    }
}
