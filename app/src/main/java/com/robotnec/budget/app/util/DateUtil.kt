package com.robotnec.budget.app.util

import org.threeten.bp.Instant
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneOffset

/**
 * @author zak zak@swingpulse.com>
 */
object DateUtil {
    fun fromSeconds(seconds: Long): LocalDateTime {
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(seconds), ZoneOffset.UTC)
    }

    fun toSeconds(date: LocalDateTime): Long {
        return date.toEpochSecond(ZoneOffset.UTC)
    }
}
