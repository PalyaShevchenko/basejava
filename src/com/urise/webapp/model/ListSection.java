package com.urise.webapp.model;

import java.util.List;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class ListSection extends AbstractSection {

    private final List<String> items;

    public ListSection(List <String> items) {
        this.items = requireNonNull(items, "items must not be null");
    }

    public List <String> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return items.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListSection that = (ListSection) o;
        return items.equals((that.items));
    }

    @Override
    public int hashCode() {
        return Objects.hash(items);
    }
}
