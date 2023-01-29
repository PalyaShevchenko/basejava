package com.urise.webapp.model;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class Link {
    private final String name;
    private final String url;

    public Link(String name, String url) {
        this.name = requireNonNull(name, "name must not be null");
        this.url = requireNonNull(url, "url must not be null");
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Link that = (Link) o;
        return url.equals(that.url) && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url, name);
    }

    @Override
    public String toString() {
        return name + " " + url;
    }
}



