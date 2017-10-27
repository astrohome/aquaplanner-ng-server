package org.galaxysoft.aquaplannerserver.data.peripheral

import org.galaxysoft.aquaplannerserver.model.RelaisPhysicalChannel
import org.galaxysoft.aquaplannerserver.model.SelectOption
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.body
import reactor.core.publisher.Flux
import kotlin.coroutines.experimental.buildSequence

@Service
@Transactional
class RelaisService {
  fun getAllPhysicalChannels(req: ServerRequest) =
    ServerResponse.ok().body(Flux.fromIterable(buildSequence {
      RelaisPhysicalChannel.values().forEach { channel ->
        yield(SelectOption<String, String>(channel.name, channel.name))
      }
    }.asIterable()))
}
