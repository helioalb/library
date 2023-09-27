package me.helioalbano.library.repository;

import java.util.Optional;

import me.helioalbano.library.domain.Book;

public interface BookRepository {
    Book save(Book book);
    Optional<Book> findById(Long bookId);
}
