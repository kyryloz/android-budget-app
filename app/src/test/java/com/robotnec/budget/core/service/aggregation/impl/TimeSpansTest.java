package com.robotnec.budget.core.service.aggregation.impl;

import org.junit.Assert;
import org.junit.Test;
import org.threeten.bp.LocalDateTime;

/**
 * @author zak <zak@swingpulse.com>
 */
public class TimeSpansTest {

    @Test
    public void shouldCalculateSpansByDays() {
        LocalDateTime minDate = LocalDateTime.of(2017, 1, 10, 12, 45);
        LocalDateTime maxDate = LocalDateTime.of(2017, 1, 13, 18, 45);
        TimeSpans spans = TimeSpans.Companion.byDays(minDate, maxDate);
        Assert.assertEquals(4, spans.size());

        TimeSpan first = spans.get(0);
        TimeSpan last = spans.get(spans.size() - 1);

        Assert.assertEquals(LocalDateTime.of(2017, 1, 10, 0, 0), first.getStartDate());
        Assert.assertEquals(LocalDateTime.of(2017, 1, 11, 0, 0), first.getEndDate());

        Assert.assertEquals(LocalDateTime.of(2017, 1, 13, 0, 0), last.getStartDate());
        Assert.assertEquals(LocalDateTime.of(2017, 1, 14, 0, 0), last.getEndDate());
    }

    @Test
    public void shouldGotYearTimeSpanIfDifferenceBetweenDatesMoreThanYear() {
        LocalDateTime minDate = LocalDateTime.of(2015, 1, 10, 12, 45);
        LocalDateTime maxDate = LocalDateTime.of(2017, 1, 13, 18, 45);
        TimeSpans spans = TimeSpans.Companion.byDays(minDate, maxDate);

        TimeSpan first = spans.get(0);
        TimeSpan last = spans.get(spans.size() - 1);

        Assert.assertEquals(LocalDateTime.of(2016, 1, 13, 0, 0), first.getStartDate());
        Assert.assertEquals(LocalDateTime.of(2016, 1, 14, 0, 0), first.getEndDate());

        Assert.assertEquals(LocalDateTime.of(2017, 1, 13, 0, 0), last.getStartDate());
        Assert.assertEquals(LocalDateTime.of(2017, 1, 14, 0, 0), last.getEndDate());
    }
}