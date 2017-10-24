package org.galaxysoft.aquaplannerserver.web

import org.galaxysoft.aquaplannerserver.data.LedChannelService
import org.galaxysoft.aquaplannerserver.data.LedTaskService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.body
import org.springframework.web.reactive.function.server.router
import reactor.core.publisher.Mono

@Configuration
class Routes(val ledTaskService: LedTaskService,
             val ledChannelService: LedChannelService) {

  @Bean
  fun router() = router {
    accept(APPLICATION_JSON).nest {
      GET("/api/ok", { ok().body(Mono.just(OK("OK"))) })
      GET("/api/tasks", ledTaskService::findAll)
      GET("/api/tasks/{id}", ledTaskService::findById)
      DELETE("/api/tasks/{id}", ledTaskService::deleteById)
      POST("/api/tasks", ledTaskService::create)
      PATCH("/api/tasks/{id}", ledTaskService::update)

      GET("/api/physical-channels", ledChannelService::getAllPhysicalChannels)
      GET("/api/channels", ledChannelService::findAll)
      GET("/api/channels/{id}", ledChannelService::findById)
      DELETE("/api/channels/{id}", ledChannelService::deleteById)
      POST("/api/channels", ledChannelService::create)
      PATCH("/api/channels/{id}", ledChannelService::update)
    }
    resources("/**", ClassPathResource("static/"))
  }

}
