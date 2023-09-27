package me.helioalbano.library.domain;

public class Book {
    private Long id;
    private String title;

    public Book(final Long id, final String title) {
        this.id = id;
        setTitle(title);
    }

    public Book(final String title) {
        this(null, title);
    }

    private void setTitle(final String title) {
        if (title == null) {
            throw new IllegalArgumentException("Book title cannot be null");
        }

        if (title.isEmpty()) {
            throw new IllegalArgumentException("Book title cannot be empty");
        }

        if (title.length() > 100) {
            throw new IllegalArgumentException(
                "Book title cannot be greater than 100");
        }

        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
