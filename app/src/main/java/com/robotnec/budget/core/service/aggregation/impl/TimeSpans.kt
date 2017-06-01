package com.robotnec.budget.core.service.aggregation.impl

import org.threeten.bp.LocalDateTime
import org.threeten.bp.temporal.ChronoUnit
import java.util.*

/**
 * @author zak zak@swingpulse.com>
 */
internal class TimeSpans private constructor(private val spans: List<TimeSpan>) : Iterable<TimeSpan> {

    fun size(): Int {
        return spans.size
    }

    operator fun get(index: Int): TimeSpan {
        return spans[index]
    }

    override fun iterator(): Iterator<TimeSpan> {
        return spans.iterator()
    }

    companion object {

        fun byDays(minDate: LocalDateTime, maxDate: LocalDateTime): TimeSpans {
            if (minDate.isAfter(maxDate)) {
                throw IllegalArgumentException("minDate > maxDate")
            }
            var minDay = minDate.truncatedTo(ChronoUnit.DAYS)
            val maxDay = maxDate.truncatedTo(ChronoUnit.DAYS).plusDays(1)

            if (ChronoUnit.YEARS.between(minDate, maxDate) > 1) {
                minDay = maxDate.minusYears(1).truncatedTo(ChronoUnit.DAYS)
            }

            val spans = ArrayList<TimeSpan>()
            var ongoing = minDay
            do {
                spans.add(TimeSpan.of(ongoing, ongoing.plusDays(1)))
                ongoing = ongoing.plusDays(1)
            } while (!ongoing.isEqual(maxDay))
            return TimeSpans(spans)
        }
    }
}
