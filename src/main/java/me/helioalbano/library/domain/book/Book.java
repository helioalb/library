package me.helioalbano.library.domain.book;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import me.helioalbano.library.domain.author.Author;

public class Book {
    private Long id;
    private Title title;
    private Set<Author> authors;

    public Book(final Long id, final Title title) {
        this.id = id;
        setTitle(title);
    }

    public Book(final Title title) {
        this(null, title);
    }

    private void setTitle(final Title title) {
        if (title == null) {
            throw new IllegalArgumentException("Book title cannot be null");
        }

        this.title = title;
    }

    public Long id() {
        return id;
    }

    public String title() {
        return title.toString();
    }

    public void addAuthor(Author author) {
        if (authors == null)
            authors = new HashSet<>();
        authors.add(author);
    }

    public Set<Author> authors() {
        return Collections.unmodifiableSet(authors);
    }
}
