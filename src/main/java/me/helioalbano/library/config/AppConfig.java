package me.helioalbano.library.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import me.helioalbano.library.infra.repository.BookRepositoryInMemory;
import me.helioalbano.library.repository.BookRepository;
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
}
