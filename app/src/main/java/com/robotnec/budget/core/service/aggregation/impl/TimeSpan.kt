package com.robotnec.budget.core.service.aggregation.impl

import org.threeten.bp.LocalDateTime

/**
 * @author zak zak@swingpulse.com>
 */
data class TimeSpan constructor(val startDate: LocalDateTime, val endDate: LocalDateTime) : Comparable<TimeSpan> {
    override fun compareTo(other: TimeSpan): Int {
        return startDate.compareTo(other.startDate)
    }

    fun isInSpan(date: LocalDateTime): Boolean {
        return (date.isEqual(startDate) || date.isAfter(startDate)) && date.isBefore(endDate)
    }

    companion object {

        fun of(from: LocalDateTime, to: LocalDateTime): TimeSpan {
            if (to.isBefore(from) || to.isEqual(from)) {
                throw IllegalArgumentException("to >= from")
            }
            return TimeSpan(from, to)
        }
    }
}
