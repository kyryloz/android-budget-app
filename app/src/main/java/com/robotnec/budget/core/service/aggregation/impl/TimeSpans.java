package com.robotnec.budget.core.service.aggregation.impl;

import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.ZoneId;
import org.threeten.bp.temporal.ChronoField;
import org.threeten.bp.temporal.ChronoUnit;

/**
 * @author zak <zak@swingpulse.com>
 */
class TimeSpans {

    static TimeSpans dyDays(LocalDate minDate, LocalDate maxDate) {
        TimeSpans spans = new TimeSpans();
        LocalDateTime minDay = minDate.atTime(0, 0);
        LocalDateTime maxDay = minDate.atTime(24, 59);

        LocalDateTime ongoing = minDay;
        do {
            spans.add(TimeSpan.of(ongoing, ongoing.plusDays(1)));
            ongoing = minDay.plusDays(1);
        } while (ChronoUnit.DAYS.between(ongoing, maxDay) != 0);
        return spans;
    }
}
