package org.galaxysoft.aquaplannerserver.data.led

import org.galaxysoft.aquaplannerserver.model.HSL
import org.galaxysoft.aquaplannerserver.model.LedChannel
import org.galaxysoft.aquaplannerserver.model.LedTask
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface LedTaskRepository : CrudRepository<LedTask, Int> {
  fun findAllByOrderByAtTimeAsc(): List<LedTask>
  fun findAllByOrderByAtTimeDesc(): List<LedTask>
}

@Repository
interface LedChannelRepository : CrudRepository<LedChannel, Int>

@Repository
interface HslRepository : CrudRepository<HSL, Int>
