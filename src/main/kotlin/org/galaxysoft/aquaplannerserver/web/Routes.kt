package org.galaxysoft.aquaplannerserver.web

import org.galaxysoft.aquaplannerserver.data.TaskService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.http.MediaType.*
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.router
import org.springframework.web.reactive.function.server.ServerResponse.*
import org.springframework.web.reactive.function.server.body
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Configuration
class Routes(val taskService: TaskService) {

    @Bean
    fun router() = router {
        accept(TEXT_HTML).nest {
            GET("/api").nest {
                accept(APPLICATION_JSON).nest {
                    GET("/ok", { ok().body(Mono.just(OK("OK"))) })
                    GET("/tasks", taskService::findAll)
                }
            }
        }
        resources("/**", ClassPathResource("static/"))
    }

}