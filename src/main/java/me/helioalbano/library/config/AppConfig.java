package me.helioalbano.library.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import me.helioalbano.library.repository.AuthorRepository;
import me.helioalbano.library.repository.BookRepository;
import me.helioalbano.library.repository.implementation.AuthorRepositoryInMemory;
import me.helioalbano.library.repository.implementation.BookRepositoryInMemory;
import me.helioalbano.library.service.AuthorService;
import me.helioalbano.library.service.BookService;

@Configuration
public class AppConfig {
    BookRepository bookRepository() {
        return new BookRepositoryInMemory();
    }

    @Bean
    BookService bookService() {
        return new BookService(bookRepository());
    }

    AuthorRepository authorRepository() {
        return new AuthorRepositoryInMemory();
    }

    @Bean
    AuthorService authorService() {
        return new AuthorService(authorRepository());
    }
}
