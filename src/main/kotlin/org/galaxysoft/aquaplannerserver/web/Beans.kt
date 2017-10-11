package org.galaxysoft.aquaplannerserver.web

import org.galaxysoft.aquaplannerserver.data.LedTaskService
import org.galaxysoft.aquaplannerserver.service.LedTasksScheduler
import org.springframework.context.support.beans
import org.springframework.web.reactive.function.server.HandlerStrategies
import org.springframework.web.reactive.function.server.RouterFunctions

fun beans() = beans {
    bean<LedTaskService>()
    bean<LedTasksScheduler>()
    bean<Routes>()
    bean("webHandler") {
        RouterFunctions.toWebHandler(
                ref<Routes>().router(),
                HandlerStrategies.builder().build()
        )
    }
}
