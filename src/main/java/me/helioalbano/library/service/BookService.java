package me.helioalbano.library.service;

import java.util.Optional;

import me.helioalbano.library.domain.book.Book;
import me.helioalbano.library.domain.book.Title;
import me.helioalbano.library.repository.BookRepository;

public class BookService {
    private BookRepository bookRepository;

    public BookService(final BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Long createBook(String title) {
        var newBook = new Book(new Title(title));
        var savedBook = bookRepository.save(newBook);

        return savedBook.getId();
    }

    public Optional<Book> findBookById(Long id) {
        return bookRepository.findById(id);
    }
}
