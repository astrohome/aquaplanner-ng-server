package org.galaxysoft.aquaplannerserver.service

import org.galaxysoft.aquaplannerserver.model.LedTask
import org.galaxysoft.aquaplannerserver.util.TimeUtils.between
import java.time.LocalTime

object PwmCalculatorService {
    fun calcCurrentPwm(ledTask: LedTask, now: LocalTime): Int {
        val deltaPerMinute = (ledTask.endPwm - ledTask.startPwm).toFloat() /
                (between(ledTask.startTime, ledTask.endTime))
        val delta = deltaPerMinute * between(ledTask.startTime, now)
        return (delta + ledTask.startPwm).toInt()
    }
}
