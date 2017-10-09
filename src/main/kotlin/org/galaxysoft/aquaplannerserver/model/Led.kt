package org.galaxysoft.aquaplannerserver.model

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table
data class LedTask(@Id @GeneratedValue val id: Int,
                   val channel: Int,
                   val startPwm: Int,
                   val endPwm: Int,
                   @JsonFormat(pattern = "HH:mm")
                   val startTime: LocalTime,
                   @JsonFormat(pattern = "HH:mm")
                   val endTime: LocalTime)

enum class PhysicalChannel {
    C1,C2,C3,C4,C5,C6,C7,C8
}

@Entity
@Table
data class LedChannel(@Id @GeneratedValue val value: Int,
                val text: String?,
                val channel: PhysicalChannel)
