package me.helioalbano.library.domain.author;

public class Name {
    private String name;

    public Name(String name) {
        setName(name);
    }

    private void setName(String name) {
        if (name == null)
            throw new IllegalArgumentException("Author name cannot be null");

        if (name.isBlank())
            throw new IllegalArgumentException("Author name cannot be blank");

        if (name.length() > 100)
            throw new IllegalArgumentException("Author name cannot be greater than 100");

        this.name = name.trim();
    }

    public String full() {
        return name;
    }
}
