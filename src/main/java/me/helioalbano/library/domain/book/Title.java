package me.helioalbano.library.domain.book;

public class Title {
    private String title;

    public Title(final String title) {
        setTitle(title);
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

    @Override
    public String toString() {
        return title;
    }
}
