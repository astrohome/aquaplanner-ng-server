package org.galaxysoft.aquaplannerserver.service

import org.galaxysoft.aquaplannerserver.data.LedTaskRepository
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class LedTasksScheduler(val ledTaskRepository: LedTaskRepository) {

    private val logger = LoggerFactory.getLogger(LedTasksScheduler::class.java)

    @Scheduled(fixedRate = 10000)
    fun whatTime() {
      /*val now = LocalTime.now()
      val ledTasks = ledTaskRepository.findAll()

      val activeTasks = ledTasks.filter { isInInterval(it.startTime, it.endTime, now) }
              .associateBy({ it.channel }, { PwmCalculatorService.calcCurrentPwm(it, now) })

      LedPhysicalChannel.values().forEach {
          if (activeTasks.containsKey(it)) {
              logger.info("Setting PWM ${activeTasks[it]} for ${it.ordinal + 1} channel")
          } else {
              logger.info("No tasks for ${it.ordinal + 1} channel")
          }
      }*/
    }


}
