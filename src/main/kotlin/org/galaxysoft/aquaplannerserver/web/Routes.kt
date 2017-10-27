package org.galaxysoft.aquaplannerserver.web

import org.galaxysoft.aquaplannerserver.data.led.LedChannelService
import org.galaxysoft.aquaplannerserver.data.led.LedTaskService
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
    "/api".nest {
      accept(APPLICATION_JSON).nest {
        GET("/ok", { ok().body(Mono.just(OK("OK"))) })
        "/led".nest {
          GET("/tasks", ledTaskService::findAll)
          GET("/tasks/{id}", ledTaskService::findById)
          DELETE("/tasks/{id}", ledTaskService::deleteById)
          POST("/tasks", ledTaskService::create)
          PATCH("/tasks/{id}", ledTaskService::update)


          GET("/physical-channels", ledChannelService::getAllPhysicalChannels)
          GET("/channels", ledChannelService::findAll)
          GET("/channels/{id}", ledChannelService::findById)
          DELETE("/channels/{id}", ledChannelService::deleteById)
          POST("/channels", ledChannelService::create)
          PATCH("/channels/{id}", ledChannelService::update)
        }
      }
    }
    //resources("/**", ClassPathResource("static/"))
  }

}
