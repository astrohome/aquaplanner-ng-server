package org.galaxysoft.aquaplannerserver.model

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
                val startTime: LocalTime,
                val endTime: LocalTime)