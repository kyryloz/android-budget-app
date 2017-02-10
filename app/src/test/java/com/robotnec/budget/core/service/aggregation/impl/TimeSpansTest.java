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
        TimeSpans spans = TimeSpans.dyDays(minDate, maxDate);
        Assert.assertEquals(4, spans.size());
    }

}