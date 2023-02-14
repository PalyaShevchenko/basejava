package com.urise.webapp.model;

import java.io.Serializable;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class Link implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String name;
    private final String url;

    public Link(String name, String url) {
        this.name = requireNonNull(name, "name must not be null");
        this.url = url;
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
        if (!name.equals(that.name)) return false;
        return url != null ? url.equals(that.url) : that.url == null;

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



