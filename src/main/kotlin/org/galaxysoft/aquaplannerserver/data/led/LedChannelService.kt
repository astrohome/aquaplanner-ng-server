package org.galaxysoft.aquaplannerserver.data.led

import org.galaxysoft.aquaplannerserver.model.LedChannel
import org.galaxysoft.aquaplannerserver.model.LedPhysicalChannel
import org.galaxysoft.aquaplannerserver.model.SelectOption
import org.galaxysoft.aquaplannerserver.web.OK
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.body
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import kotlin.coroutines.experimental.buildSequence

@Service
@Transactional
class LedChannelService(private val ledChannelRepository: LedChannelRepository,
                        private val hslRepository: HslRepository) {

  fun findAll(req: ServerRequest) =
    ServerResponse.ok().body(Flux.fromIterable(ledChannelRepository.findAll()))

  fun getAllPhysicalChannels(req: ServerRequest) =
    ServerResponse.ok().body(Flux.fromIterable(buildSequence {
      LedPhysicalChannel.values().forEach { channel ->
        yield(SelectOption<String, String>(channel.name, channel.name))
      }
    }.asIterable()))

  fun create(req: ServerRequest) =
    req.bodyToMono(LedChannel::class.java).doOnNext { task ->
      hslRepository.save(task.color)
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
      hslRepository.save(task.color)
      ledChannelRepository.save(task)
    }.then(ServerResponse.ok().body(Mono.just(OK())))
}
