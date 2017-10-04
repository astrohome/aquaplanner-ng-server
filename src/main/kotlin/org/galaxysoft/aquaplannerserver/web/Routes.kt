package org.galaxysoft.aquaplannerserver.web

import org.galaxysoft.aquaplannerserver.data.TaskService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.http.MediaType.*
import org.springframework.web.reactive.function.server.router
import org.springframework.web.reactive.function.server.ServerResponse.*
import org.springframework.web.reactive.function.server.body
import reactor.core.publisher.Mono

@Configuration
class Routes(val taskService: TaskService) {

    @Bean
    fun router() = router {
        accept(APPLICATION_JSON).nest {
            GET("/api/ok", { ok().body(Mono.just(OK("OK"))) })
                    GET("/api/tasks", taskService::findAll)
                    GET("/api/tasks/{id}", taskService::findById)
                    DELETE("/api/tasks/{id}", taskService::deleteById)
                    POST("/api/tasks", taskService::create)
                    PUT("/api/tasks/{id}", taskService::update)
        }
        resources("/**", ClassPathResource("static/"))
    }

}