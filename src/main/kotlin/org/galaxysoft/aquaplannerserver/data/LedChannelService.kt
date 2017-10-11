package org.galaxysoft.aquaplannerserver.data

import org.galaxysoft.aquaplannerserver.model.LedChannel
import org.galaxysoft.aquaplannerserver.web.OK
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.body
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
@Transactional
class LedChannelService(private val ledChannelRepository: LedChannelRepository) {

    fun findAll(req: ServerRequest) =
        ServerResponse.ok().body(Flux.fromIterable(ledChannelRepository.findAll()))

    fun create(req: ServerRequest) =
            req.bodyToMono(LedChannel::class.java).doOnNext { task ->
                ledChannelRepository.save(task)
            }.then(ServerResponse.ok().body(Mono.empty()))

    fun findById(req: ServerRequest) =
            ServerResponse.ok().body(Mono.just(ledChannelRepository.findById(req.pathVariable("id").toInt())))

    fun deleteById(req: ServerRequest): Mono<ServerResponse> {
        val id = req.pathVariable("id").toInt()
        ledChannelRepository.deleteById(id)
        return ok().body(Mono.just(OK()))
    }

    fun update(req: ServerRequest) =
            req.bodyToMono(LedChannel::class.java).doOnNext { task ->
                ledChannelRepository.save(task)
            }.then(ServerResponse.ok().body(Mono.just(OK())))
}
