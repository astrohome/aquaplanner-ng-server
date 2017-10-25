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
                   val channel: LedPhysicalChannel,
                   val pwm: Int,
                   @JsonFormat(pattern = "HH:mm")
                   val atTime: LocalTime)

enum class LedPhysicalChannel {
  C1, C2, C3, C4, C5, C6, C7, C8, C9
}

data class SelectOption<out ID, out VALUE>(val id: ID, val text: VALUE)

@Entity
@Table
data class LedChannel(@Id @GeneratedValue val id: Int,
                      val name: String,
                      val hue: Int,
                      val saturation: Int,
                      val lightness: Int,
                      val channel: LedPhysicalChannel)
