package org.galaxysoft.aquaplannerserver.data

import org.galaxysoft.aquaplannerserver.model.Task
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository


@Repository
interface TaskRepository : CrudRepository<Task, Int>