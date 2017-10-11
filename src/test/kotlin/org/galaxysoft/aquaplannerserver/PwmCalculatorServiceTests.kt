package org.galaxysoft.aquaplannerserver

import org.galaxysoft.aquaplannerserver.model.LedTask
import org.galaxysoft.aquaplannerserver.service.PwmCalculatorService
import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.LocalTime

class PwmCalculatorServiceTests {

    @Test
    fun testPwmCalculation1() {
        val task = LedTask(1,1,10,70, LocalTime.of(9,0), LocalTime.of(10,0))
        val calcCurrentPwm = PwmCalculatorService.calcCurrentPwm(task, LocalTime.of(9, 30))
        assertEquals(40, calcCurrentPwm)
    }

    @Test
    fun testPwmCalculation2() {
        val task = LedTask(1,1,70,10, LocalTime.of(9,0), LocalTime.of(10,0))
        val calcCurrentPwm = PwmCalculatorService.calcCurrentPwm(task, LocalTime.of(9, 30))
        assertEquals(40, calcCurrentPwm)
    }

    @Test
    fun testPwmCalculation3() {
        val task = LedTask(1,1,70,10, LocalTime.of(23,0), LocalTime.of(0,0))
        val calcCurrentPwm = PwmCalculatorService.calcCurrentPwm(task, LocalTime.of(23, 30))
        assertEquals(40, calcCurrentPwm)
    }

    @Test
    fun testPwmCalculation4() {
        val task = LedTask(1,1,0,40, LocalTime.of(9,0), LocalTime.of(11,0))
        val calcCurrentPwm = PwmCalculatorService.calcCurrentPwm(task, LocalTime.of(10, 4))
        assertEquals(21, calcCurrentPwm)
        val calcCurrentPwm2 = PwmCalculatorService.calcCurrentPwm(task, LocalTime.of(11, 0))
        assertEquals(40, calcCurrentPwm2)
    }
}
