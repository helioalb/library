package me.helioalbano.library.service;

import me.helioalbano.library.domain.book.Book;
import me.helioalbano.library.domain.book.Title;
import me.helioalbano.library.repository.BookRepository;
import me.helioalbano.library.service.exception.BookNotFoundException;

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

    public Book getBookById(Long id) {
        var book = bookRepository.findById(id);
        if (book.isEmpty())
            throw new BookNotFoundException();

        return book.get();
    }
}
