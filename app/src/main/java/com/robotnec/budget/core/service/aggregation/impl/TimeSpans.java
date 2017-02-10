package com.robotnec.budget.core.service.aggregation.impl;

import org.threeten.bp.LocalDateTime;
import org.threeten.bp.temporal.ChronoUnit;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author zak <zak@swingpulse.com>
 */
class TimeSpans implements Iterable<TimeSpan> {

    private final List<TimeSpan> spans;

    static TimeSpans dyDays(LocalDateTime minDate, LocalDateTime maxDate) {
        LocalDateTime minDay = minDate.truncatedTo(ChronoUnit.DAYS);
        LocalDateTime maxDay = maxDate.truncatedTo(ChronoUnit.DAYS).plusDays(1);

        List<TimeSpan> spans = new ArrayList<>();
        LocalDateTime ongoing = minDay;
        do {
            spans.add(TimeSpan.of(ongoing, ongoing.plusDays(1)));
            ongoing = ongoing.plusDays(1);
        } while (!ongoing.isEqual(maxDay));
        return new TimeSpans(spans);
    }

    private TimeSpans(List<TimeSpan> spans) {
        this.spans = spans;
    }

    public int size() {
        return spans.size();
    }

    public TimeSpan get(int index) {
        return spans.get(index);
    }

    @Override
    public Iterator<TimeSpan> iterator() {
        return spans.iterator();
    }
}
