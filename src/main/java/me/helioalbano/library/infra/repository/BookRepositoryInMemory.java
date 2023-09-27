package me.helioalbano.library.infra.repository;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import me.helioalbano.library.domain.Book;
import me.helioalbano.library.repository.BookRepository;

public class BookRepositoryInMemory implements BookRepository {

    private final static Map<Long, Book> books = new LinkedHashMap<>();

    @Override
    public Book save(Book book) {
        Long id = book.getId();

        if (id == null) {
            id = (long) (books.size() + 1);
        }

        books.put(id, new Book(id, book.getTitle()));
        return books.get(id);
    }

    @Override
    public Optional<Book> findById(Long bookId) {
        return Optional.ofNullable(books.get(bookId));
    }

}
