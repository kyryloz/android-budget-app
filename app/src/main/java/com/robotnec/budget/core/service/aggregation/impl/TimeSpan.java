package com.robotnec.budget.core.service.aggregation.impl;

import org.threeten.bp.LocalDateTime;

/**
 * @author zak <zak@swingpulse.com>
 */
public final class TimeSpan implements Comparable<TimeSpan> {
    private final LocalDateTime from;
    private final LocalDateTime to;

    public static TimeSpan of(LocalDateTime from, LocalDateTime to) {
        if (to.isBefore(from) || to.isEqual(from)) {
            throw new IllegalArgumentException("to >= from");
        }
        return new TimeSpan(from, to);
    }

    private TimeSpan(LocalDateTime from, LocalDateTime to) {
        this.from = from;
        this.to = to;
    }

    public boolean isInSpan(LocalDateTime date) {
        return (date.isEqual(from) || date.isAfter(from)) && date.isBefore(to);
    }

    public LocalDateTime getStartDate() {
        return from;
    }

    public LocalDateTime getEndDate() {
        return to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TimeSpan timeSpan = (TimeSpan) o;

        if (from != null ? !from.equals(timeSpan.from) : timeSpan.from != null) return false;
        return to != null ? to.equals(timeSpan.to) : timeSpan.to == null;
    }

    @Override
    public int hashCode() {
        int result = from != null ? from.hashCode() : 0;
        result = 31 * result + (to != null ? to.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(TimeSpan o) {
        return from.compareTo(o.from);
    }
}
