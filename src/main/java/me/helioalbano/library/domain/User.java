package me.helioalbano.library.domain;

import me.helioalbano.library.domain.author.Name;

public class User {
    private Long id;
    private String name;

    public User() {}
    public User(Long id, String name) {
        setId(id);
        setName(name);
    }

    public User(String name) {
        this(null, name);
    }

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return new Name(name).full();
    }

    private void setName(String name) {
        this.name = name;
    }

}
