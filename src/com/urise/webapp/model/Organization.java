package com.urise.webapp.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.urise.webapp.util.DateUtil.NOW;
import static com.urise.webapp.util.DateUtil.of;
import static java.util.Objects.requireNonNull;

public class Organization implements Serializable {

    private static final long serialVersionUID = 1L;
    private List<Period> periods = new ArrayList<>();
    private final Link link;

    public Organization(String name, String url, Period... periods) {
        this(new Link(name, url), Arrays.asList(periods));
    }

    public Organization(Link link, List<Period> periods) {
        this.link = link;
        this.periods = periods;
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
        return link.equals(that.link) && periods.equals(that.periods);
    }

    @Override
    public int hashCode() {
        return Objects.hash(link, periods);
    }

    @Override
    public String toString() {
        return link + " " + periods;
    }

    public static class Period implements Serializable {
        private static final long serialVersionUID = 1L;
        private final LocalDate startDate;
        private final LocalDate endDate;
        private final String title;
        private final String description;

        public Period(int startYear, Month startMonth, String title, String description) {
            this(of(startYear, startMonth), NOW, title, description);
        }

        public Period(int startYear, Month startMonth, int endYear, Month endMonth, String title, String description) {
            this(of(startYear, startMonth), of(endYear, endMonth), title, description);
        }

        public Period (LocalDate startDate, LocalDate endDate, String title, String description) {
            this.startDate = requireNonNull(startDate, "startDate must not be null");
            this.endDate = requireNonNull(endDate, "endDate must not be null");
            this.title = requireNonNull(title, "title must not be null");
            this.description = description == null ? "" : description;
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
}
