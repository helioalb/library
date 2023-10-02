package me.helioalbano.library.entrypoint.rest;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import me.helioalbano.library.domain.book.Book;
import me.helioalbano.library.entrypoint.rest.dto.BookResponse;
import me.helioalbano.library.entrypoint.rest.dto.CreateBookRequest;
import me.helioalbano.library.service.BookService;

@Controller
@RequestMapping("books")
public class BooksController {

    private BookService bookService;

    public BooksController(final BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/")
    ResponseEntity<Void> create(@Validated @RequestBody CreateBookRequest input) {
        var bookId = bookService.createBook(input.title());
        return ResponseEntity.created(buildResourcePath(bookId)).build();
    }

    private URI buildResourcePath(Long bookId) {
        return URI.create("/books/" + bookId);
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<BookResponse> show(@PathVariable("bookId") Long bookId) {
        var book = bookService.findBookById(bookId);
        if (book.isPresent())
            return ResponseEntity.ok(buildBookResponse(book.get()));
        return ResponseEntity.notFound().build();
    }

    private BookResponse buildBookResponse(Book book) {
        return new BookResponse(book.getTitle().toString());
    }
}
