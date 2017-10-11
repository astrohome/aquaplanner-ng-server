package org.galaxysoft.aquaplannerserver.util

import java.time.LocalTime
import java.time.temporal.ChronoUnit

object TimeUtils {

    fun between(startTime: LocalTime, endTime: LocalTime): Int {
        return if (endTime.isBefore(startTime)) {
            (ChronoUnit.MINUTES.between(startTime, LocalTime.of(23, 59)) +
                    ChronoUnit.MINUTES.between(LocalTime.of(0, 0), endTime) + 1).toInt()
        } else {
            ChronoUnit.MINUTES.between(startTime, endTime).toInt()
        }
    }

    fun isInInterval(start: LocalTime, end: LocalTime, timeToTest: LocalTime): Boolean {
        return (between(start, timeToTest) + between(timeToTest, end) <= between(start, end))
    }
}
