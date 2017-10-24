package org.galaxysoft.aquaplannerserver

import org.galaxysoft.aquaplannerserver.model.LedPhysicalChannel
import org.galaxysoft.aquaplannerserver.model.LedTask
import org.galaxysoft.aquaplannerserver.service.PwmCalculatorService
import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.LocalTime

class PwmCalculatorServiceTests {

  @Test
  fun testPwmCalculation1() {
    val task1 = LedTask(1, LedPhysicalChannel.C1, 10, LocalTime.of(9, 0))
    val task2 = LedTask(2, LedPhysicalChannel.C1, 70, LocalTime.of(10, 0))
    val calcCurrentPwm = PwmCalculatorService.calcCurrentPwm(task1, task2, LocalTime.of(9, 30))
    assertEquals(40, calcCurrentPwm)
  }

  @Test
  fun testPwmCalculation2() {
    val task1 = LedTask(1, LedPhysicalChannel.C1, 70, LocalTime.of(9, 0))
    val task2 = LedTask(2, LedPhysicalChannel.C1, 10, LocalTime.of(10, 0))

    val calcCurrentPwm = PwmCalculatorService.calcCurrentPwm(task1, task2, LocalTime.of(9, 30))
    assertEquals(40, calcCurrentPwm)
  }

  @Test
  fun testPwmCalculation3() {
    val task1 = LedTask(1, LedPhysicalChannel.C1, 70, LocalTime.of(23, 0))
    val task2 = LedTask(2, LedPhysicalChannel.C1, 10, LocalTime.of(0, 0))


    val calcCurrentPwm = PwmCalculatorService.calcCurrentPwm(task1, task2, LocalTime.of(23, 30))
    assertEquals(40, calcCurrentPwm)
  }

  @Test
  fun testPwmCalculation4() {
    val task1 = LedTask(1, LedPhysicalChannel.C1, 0, LocalTime.of(9, 0))
    val task2 = LedTask(2, LedPhysicalChannel.C1, 40, LocalTime.of(11, 0))

    val calcCurrentPwm = PwmCalculatorService.calcCurrentPwm(task1, task2, LocalTime.of(10, 4))
    assertEquals(21, calcCurrentPwm)
    val calcCurrentPwm2 = PwmCalculatorService.calcCurrentPwm(task1, task2, LocalTime.of(11, 0))
    assertEquals(40, calcCurrentPwm2)
  }
}
