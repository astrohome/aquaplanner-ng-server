package org.galaxysoft.aquaplannerserver.data

import org.galaxysoft.aquaplannerserver.model.LedTask
import org.galaxysoft.aquaplannerserver.web.OK
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse.*
import org.springframework.web.reactive.function.server.body
import reactor.core.publisher.Mono

@Service
@Transactional
class LedTaskService(private val ledTaskRepository: LedTaskRepository) {

    fun findAll(req: ServerRequest) =
        ok().body(Flux.fromIterable(ledTaskRepository.findAll()))

    fun create(req: ServerRequest) =
            req.bodyToMono(LedTask::class.java).doOnNext { task ->
                ledTaskRepository.save(task)
            }.then(ok().body(Mono.empty()))

    fun findById(req: ServerRequest) =
            ok().body(Mono.just(ledTaskRepository.findById(req.pathVariable("id").toInt())))

    fun deleteById(req: ServerRequest) =
            req.bodyToMono(LedTask::class.java).doOnNext { task ->
                ledTaskRepository.delete(task)
            }.then(ok().body(Mono.just(OK())))

    fun update(req: ServerRequest) =
            req.bodyToMono(LedTask::class.java).doOnNext { task ->
                ledTaskRepository.save(task)
            }.then(ok().body(Mono.just(OK())))
}
