package org.galaxysoft.aquaplannerserver

import org.galaxysoft.aquaplannerserver.web.beans
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.GenericApplicationContext
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.http.server.reactive.HttpHandler
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter
import org.springframework.web.server.adapter.WebHttpHandlerBuilder
import reactor.ipc.netty.http.server.HttpServer
import reactor.ipc.netty.tcp.BlockingNettyContext

/*class Application(port: Int = 8080) {
    private val httpHandler: HttpHandler
    private val server: HttpServer
    private var nettyContext: BlockingNettyContext? = null

    fun start() {
        nettyContext = server.start(ReactorHttpHandlerAdapter(httpHandler))
    }

    fun startAndAwait() {
        server.startAndAwait(ReactorHttpHandlerAdapter(httpHandler),
                { nettyContext = it })
    }

    fun stop() {
        nettyContext?.shutdown()
    }

    init {
        val context = GenericApplicationContext().apply {
            beans().initialize(this)
            refresh()
        }
        server = HttpServer.create(port)
        httpHandler = WebHttpHandlerBuilder.applicationContext(context).build()
    }
}*/
@SpringBootApplication
class Application

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}
