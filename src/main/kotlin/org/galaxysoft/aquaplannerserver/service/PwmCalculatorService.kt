package org.galaxysoft.aquaplannerserver.service

import org.galaxysoft.aquaplannerserver.model.LedTask
import java.time.LocalTime
import java.time.temporal.ChronoUnit

object PwmCalculatorService {
    fun calcCurrentPwm(ledTask: LedTask, now: LocalTime): Int {
        val deltaPerMinute = (ledTask.endPwm - ledTask.startPwm).toFloat() /
                (between(ledTask.startTime, ledTask.endTime))
        val delta = deltaPerMinute * between(ledTask.startTime, now)
        return (delta + ledTask.startPwm).toInt()
    }

    fun between(startTime: LocalTime, endTime: LocalTime): Int {
        return if (endTime.isBefore(startTime)) {
            (ChronoUnit.MINUTES.between(startTime, LocalTime.of(23, 59)) +
                    ChronoUnit.MINUTES.between(LocalTime.of(0, 0), endTime) + 1).toInt()
        } else {
            ChronoUnit.MINUTES.between(startTime, endTime).toInt()
        }
    }
}