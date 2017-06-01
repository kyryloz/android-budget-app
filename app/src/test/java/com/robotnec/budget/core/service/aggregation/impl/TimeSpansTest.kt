package com.robotnec.budget.core.service.aggregation.impl

import org.junit.Assert
import org.junit.Test
import org.threeten.bp.LocalDateTime

/**
 * @author zak zak@swingpulse.com>
 */
class TimeSpansTest {

    @Test
    fun shouldCalculateSpansByDays() {
        val minDate = LocalDateTime.of(2017, 1, 10, 12, 45)
        val maxDate = LocalDateTime.of(2017, 1, 13, 18, 45)
        val spans = TimeSpans.byDays(minDate, maxDate)
        Assert.assertEquals(4, spans.size().toLong())

        val (startDate, endDate) = spans[0]
        val (startDate1, endDate1) = spans[spans.size() - 1]

        Assert.assertEquals(LocalDateTime.of(2017, 1, 10, 0, 0), startDate)
        Assert.assertEquals(LocalDateTime.of(2017, 1, 11, 0, 0), endDate)

        Assert.assertEquals(LocalDateTime.of(2017, 1, 13, 0, 0), startDate1)
        Assert.assertEquals(LocalDateTime.of(2017, 1, 14, 0, 0), endDate1)
    }

    @Test
    fun shouldGotYearTimeSpanIfDifferenceBetweenDatesMoreThanYear() {
        val minDate = LocalDateTime.of(2015, 1, 10, 12, 45)
        val maxDate = LocalDateTime.of(2017, 1, 13, 18, 45)
        val spans = TimeSpans.byDays(minDate, maxDate)

        val (startDate, endDate) = spans[0]
        val (startDate1, endDate1) = spans[spans.size() - 1]

        Assert.assertEquals(LocalDateTime.of(2016, 1, 13, 0, 0), startDate)
        Assert.assertEquals(LocalDateTime.of(2016, 1, 14, 0, 0), endDate)

        Assert.assertEquals(LocalDateTime.of(2017, 1, 13, 0, 0), startDate1)
        Assert.assertEquals(LocalDateTime.of(2017, 1, 14, 0, 0), endDate1)
    }
}