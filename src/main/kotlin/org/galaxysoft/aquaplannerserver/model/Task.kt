package org.galaxysoft.aquaplannerserver.model

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table
data class Task(@Id @GeneratedValue val id: Int,
                val channel: Int,
                val startPwm: Int,
                val endPwm: Int,
                @JsonFormat(pattern = "HH:mm")
                val startTime: LocalTime,
                @JsonFormat(pattern = "HH:mm")
                val endTime: LocalTime)
