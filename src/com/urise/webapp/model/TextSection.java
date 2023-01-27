package com.urise.webapp.model;

import static java.util.Objects.hash;
import static java.util.Objects.requireNonNull;

public class TextSection extends AbstractSection {

    private final String text;

    public TextSection(String text) {
        this.text = requireNonNull(text, "items must not be null");
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TextSection that = (TextSection) o;
        return text.equals((that.text));
    }

    @Override
    public int hashCode() {
        return hash(text);
    }
}
