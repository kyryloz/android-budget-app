package com.robotnec.budget.app.util;

import org.threeten.bp.Instant;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.ZoneOffset;

/**
 * @author zak <zak@swingpulse.com>
 */
public class DateUtil {
    public static LocalDateTime fromSeconds(long seconds) {
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(seconds), ZoneOffset.UTC);
    }

    public static long toSeconds(LocalDateTime date) {
        return date.toEpochSecond(ZoneOffset.UTC);
    }
}
