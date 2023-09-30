package me.helioalbano.library.domain.book;

public class Book {
    private Long id;
    private Title title;

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

    public Long getId() {
        return id;
    }

    public Title getTitle() {
        return title;
    }
}
