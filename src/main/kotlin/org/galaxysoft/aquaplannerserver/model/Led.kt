package org.galaxysoft.aquaplannerserver.model

import com.fasterxml.jackson.annotation.JsonFormat
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import java.time.LocalTime
import javax.persistence.*

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
data class HSL(@Id @GeneratedValue val id: Int,
               val hue: Int,
               val saturation: Int,
               val lightness: Int)

@Entity
@Table
data class LedChannel(@Id @GeneratedValue val id: Int,
                      val name: String,
                      @ManyToOne
                      @OnDelete(action = OnDeleteAction.CASCADE)
                      @JoinColumn(name = "HSL_id")
                      val color: HSL,
                      val channel: LedPhysicalChannel)
