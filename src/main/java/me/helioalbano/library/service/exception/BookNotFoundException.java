package me.helioalbano.library.service.exception;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException() {
        super("book_not_found");
    }
}
