package org.galaxysoft.aquaplannerserver

import org.galaxysoft.aquaplannerserver.data.LedTaskRepository
import org.galaxysoft.aquaplannerserver.model.LedTask
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.time.Duration
import java.time.LocalTime

@Component
class TestScheduler(val ledTaskRepository: LedTaskRepository) {

    private val logger = LoggerFactory.getLogger(TestScheduler::class.java)

    @Scheduled(fixedRate = 60000)
    fun whatTime() {
        val now = LocalTime.now()
        val ledTasks = ledTaskRepository.findAll()

        ledTasks.filter { it.startTime <= now && it.endTime >= now }
                .map { it.channel to (it.startPwm - it.endPwm)}
    }


}
