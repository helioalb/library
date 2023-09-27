package me.helioalbano.library.service;

import me.helioalbano.library.domain.Book;
import me.helioalbano.library.repository.BookRepository;

public class BookService {
    private BookRepository bookRepository;

    public BookService(final BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Long createBook(String title) {
        var newBook = new Book(title);
        var savedBook = bookRepository.save(newBook);

        return savedBook.getId();
    }
}
