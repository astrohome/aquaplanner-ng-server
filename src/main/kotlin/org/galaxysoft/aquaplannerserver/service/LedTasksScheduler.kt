package org.galaxysoft.aquaplannerserver.service

import org.galaxysoft.aquaplannerserver.data.led.LedTaskRepository
import org.galaxysoft.aquaplannerserver.util.TimeUtils.isInInterval
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.time.LocalTime

@Component
class LedTasksScheduler(val ledTaskRepository: LedTaskRepository) {

    private val logger = LoggerFactory.getLogger(LedTasksScheduler::class.java)

    @Scheduled(fixedRate = 10000)
    fun whatTime() {
      val now = LocalTime.now()
      val ledTasks = ledTaskRepository.findAllByOrderByAtTimeAsc().groupBy{ it.channel }
      val ledTasks2 = ledTaskRepository.findAllByOrderByAtTimeDesc().groupBy{ it.channel }

        ledTasks.filter(fun(item):Boolean {
          val tasks = item.value
          return (0..tasks.size step 2)
            .firstOrNull()
            ?.let {
              if (it == tasks.size - 1)
                isInInterval(tasks[0].atTime, tasks[it].atTime, now)
              else
                isInInterval(tasks[it+1].atTime, tasks[it].atTime, now)
            }
            ?: false
        }).forEach { channel, tasks -> run {
          (0..tasks.size step 2)
            .firstOrNull()
            ?.let {
              if (it == tasks.size - 1) {
                val pwm = PwmCalculatorService.calcCurrentPwm(tasks[it], tasks[0], now)
                logger.info("Setting PWM $pwm for ${channel.name} channel")
              }
              else
              {
                val pwm = PwmCalculatorService.calcCurrentPwm(tasks[it], tasks[it+1], now)
                logger.info("Setting PWM $pwm for ${channel.name} channel")
              }
            }
            ?: logger.info("No tasks for ${channel.name} channel")
        } }
    }


}
