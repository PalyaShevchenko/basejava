package com.urise.webapp.model;

import java.time.LocalDate;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class Period {

    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String title;
    private final String description;

    public Period (LocalDate startDate, LocalDate endDate, String title, String description) {
        this.startDate = requireNonNull(startDate, "startDate must not be null");
        this.endDate = requireNonNull(endDate, "endDate must not be null");
        this.title = requireNonNull(title, "title must not be null");
        this.description = description == null ? "" : description;
    }

    public Period(int startYear, int startMonth, int startDay, int endYear, int endMonth, int endDay, String title, String description) {
        this(LocalDate.of(startYear, startMonth, startDay), LocalDate.of(endYear, endMonth, endDay), title, description);
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Period that = (Period) o;
        return startDate.equals(that.startDate) && endDate.equals(that.endDate) &&
                title.equals(that.title) && description.equals(that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate, title);
    }

    @Override
    public String toString() {
        return startDate + " " + endDate + " " + title  + " " + description;
    }
}
