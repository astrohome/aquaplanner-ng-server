package org.galaxysoft.aquaplannerserver.data

import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse.*
import org.springframework.web.reactive.function.server.body

@Service
@Transactional
class TaskService(private val taskRepository: TaskRepository) {

    fun findAll(req: ServerRequest) =
        ok().body(Flux.fromIterable(taskRepository.findAll()))
}