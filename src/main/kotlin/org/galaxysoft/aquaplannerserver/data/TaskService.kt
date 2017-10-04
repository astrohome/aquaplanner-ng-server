package org.galaxysoft.aquaplannerserver.data

import org.galaxysoft.aquaplannerserver.model.Task
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse.*
import org.springframework.web.reactive.function.server.body
import reactor.core.publisher.Mono

@Service
@Transactional
class TaskService(private val taskRepository: TaskRepository) {

    fun findAll(req: ServerRequest) =
        ok().body(Flux.fromIterable(taskRepository.findAll()))

    fun create(req: ServerRequest) =
            req.bodyToMono(Task::class.java).doOnNext { task ->
                taskRepository.save(task)
            }.then(ok().body(Mono.empty()))
}