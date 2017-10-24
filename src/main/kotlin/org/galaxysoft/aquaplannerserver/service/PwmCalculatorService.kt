package org.galaxysoft.aquaplannerserver.service

import org.galaxysoft.aquaplannerserver.model.LedTask
import org.galaxysoft.aquaplannerserver.util.TimeUtils.between
import java.time.LocalTime

object PwmCalculatorService {
  fun calcCurrentPwm(current: LedTask, next: LedTask, now: LocalTime): Int {
    val deltaPerMinute = (next.pwm - current.pwm).toFloat() /
      (between(current.atTime, next.atTime))
    val delta = deltaPerMinute * between(current.atTime, now)
    return (delta + current.pwm).toInt()
    }
}
