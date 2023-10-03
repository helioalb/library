package me.helioalbano.library.domain.author;

public class Author {

    private Long id;
    private Name name;

    public Author(final Name name) {
        this(null, name);
    }

    public Author(Long id, Name name) {
        this.id = id;
        setName(name);
    }

    private void setName(final Name name) {
        if (name == null)
            throw new IllegalArgumentException("Author name cannot be null");

        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String fullName() {
        return name.full();
    }
}
