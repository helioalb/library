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

    public Long id() {
        return id;
    }

    public String fullName() {
        return name.full();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + name.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        var equal = false;
        if (obj != null && this.getClass() == obj.getClass()) {
            var other = (Author) obj;
            equal =
                (this.id() == null ? 0 : this.id()) == (other.id() == null ? 0 : other.id()) &&
                this.fullName() == other.fullName();
        }

        return equal;
    }




}
