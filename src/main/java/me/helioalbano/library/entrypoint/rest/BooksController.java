package me.helioalbano.library.entrypoint.rest;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
