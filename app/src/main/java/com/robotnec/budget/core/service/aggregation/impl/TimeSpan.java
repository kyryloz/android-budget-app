package com.robotnec.budget.core.service.aggregation.impl;

import org.threeten.bp.LocalDateTime;

/**
 * @author zak <zak@swingpulse.com>
 */
public final class TimeSpan {
    private final LocalDateTime from;
    private final LocalDateTime to;

    public static TimeSpan of(LocalDateTime from, LocalDateTime to) {
        return new TimeSpan(from, to);
    }

    private TimeSpan(LocalDateTime from, LocalDateTime to) {
        this.from = from;
        this.to = to;
    }

    public boolean isInSpan(LocalDateTime date) {
        return (date.isEqual(from) || date.isAfter(from)) && date.isBefore(to);
    }
}
