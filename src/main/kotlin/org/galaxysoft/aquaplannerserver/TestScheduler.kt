package org.galaxysoft.aquaplannerserver

import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.time.LocalTime

@Component
class TestScheduler {

    private val logger = LoggerFactory.getLogger(TestScheduler::class.java)

    @Scheduled(fixedRate = 1000)
    fun whatTime() {
        logger.warn(LocalTime.now().toString())
    }
}
