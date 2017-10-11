package org.galaxysoft.aquaplannerserver

import org.galaxysoft.aquaplannerserver.data.LedTaskRepository
import org.galaxysoft.aquaplannerserver.service.PwmCalculatorService
import org.galaxysoft.aquaplannerserver.util.TimeUtils.isInInterval
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.time.LocalTime

@Component
class TestScheduler(val ledTaskRepository: LedTaskRepository) {

    private val logger = LoggerFactory.getLogger(TestScheduler::class.java)

    @Scheduled(fixedRate = 60000)
    fun whatTime() {
        val now = LocalTime.now()
        val ledTasks = ledTaskRepository.findAll()

        ledTasks.filter { isInInterval(it.startTime, it.endTime, now) }
                .map { it.channel to PwmCalculatorService.calcCurrentPwm(it, now) }
    }


}
