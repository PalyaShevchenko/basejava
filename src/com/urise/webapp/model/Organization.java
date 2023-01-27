package com.urise.webapp.model;

import java.util.List;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class Organization {

    private final List<Period> periods;
    private final Link link;

    public Organization(Link link, List<Period> periods) {
        this.link = requireNonNull(link, "periods must not be null");
        this.periods = requireNonNull(periods, "periods must not be null");
    }

    public Link getLink() {
        return link;
    }

    public List<Period> getPeriods() {
        return periods;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return periods.equals(that.periods) && link.equals(that.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(periods, link);
    }

    @Override
    public String toString() {
        return periods + " " + link;
    }
}
